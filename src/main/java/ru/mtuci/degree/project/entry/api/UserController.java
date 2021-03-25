package ru.mtuci.degree.project.entry.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.mtuci.degree.project.entry.repository.UserRepository;
import ru.mtuci.degree.project.entry.user.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @ResponseBody
    @GetMapping("api/v1/user")
    public List<User> getUser(@PathVariable("userId") Optional<Long> userId) {
        if (userId.isPresent())
            return userId.map(id -> userRepository.findById(id).orElseThrow(() -> new IllegalStateException("No user with such id"))).stream().collect(Collectors.toList());
        return userRepository.findAll();
    }

}
