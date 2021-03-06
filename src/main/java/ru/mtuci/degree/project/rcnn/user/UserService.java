package ru.mtuci.degree.project.rcnn.user;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.mtuci.degree.project.rcnn.repository.UserRepository;
import ru.mtuci.degree.project.rcnn.token.ConfirmationToken;
import ru.mtuci.degree.project.rcnn.token.ConfirmationTokenService;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final BCryptPasswordEncoder passwordEncoder;

    private final ConfirmationTokenService tokenService;

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format("user with email %s not found", email)));
    }

    public String signUpUser(User user) {
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (userOptional.isPresent()) {
            if (!userOptional.get().equals(user)) {
                throw new IllegalStateException("email already used");
            }
            /* TODO:    reverification:
                        - make previous token expired
                        - generate and save new token
                        - send new mail
             */
        }

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );

        userRepository.save(user);
        tokenService.saveConfirmationToken(
                confirmationToken);

        return token;
    }

    public void enableUser(String email) {
        userRepository.enableUser(email);
    }
}
