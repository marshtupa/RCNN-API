package ru.mtuci.degree.project.entry.registration;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mtuci.degree.project.entry.configuration.RegistrationConfigurations;
import ru.mtuci.degree.project.entry.email.EmailBuilder;
import ru.mtuci.degree.project.entry.email.EmailService;
import ru.mtuci.degree.project.entry.email.EmailValidator;
import ru.mtuci.degree.project.entry.token.ConfirmationToken;
import ru.mtuci.degree.project.entry.token.ConfirmationTokenService;
import ru.mtuci.degree.project.entry.user.User;
import ru.mtuci.degree.project.entry.user.UserRole;
import ru.mtuci.degree.project.entry.user.UserService;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final ConfirmationTokenService tokenService;
    private final RegistrationConfigurations configuration;
    private final EmailValidator emailValidator;
    private final EmailService emailService;
    private final EmailBuilder emailBuilder;
    private final UserService userService;

    public String register(RegistrationRequest request) {
        if (!emailValidator.isValid(request.getEmail())) {
            throw new IllegalStateException("email not valid");
        }

        String token = userService.signUpUser(
                new User(request.getFirstName(), request.getLastName(), request.getEmail(), request.getPassword(), UserRole.USER));

        String verificationLink = configuration.getVerificationBaseUrl() + token;
        emailService.send(request.getEmail(), emailBuilder.buildEmail(request.getFirstName(), verificationLink));
        return "Success! We send mail to verify and activate you account";
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = tokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        tokenService.setConfirmedAt(token);
        userService.enableUser(
                confirmationToken.getUser().getEmail());
        return "confirmed";
    }
}
