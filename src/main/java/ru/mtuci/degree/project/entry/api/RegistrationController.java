package ru.mtuci.degree.project.entry.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.mtuci.degree.project.entry.registration.RegistrationRequest;
import ru.mtuci.degree.project.entry.registration.RegistrationService;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/v1/registration")
public class RegistrationController {

    private RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
}
