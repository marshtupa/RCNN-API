package ru.mtuci.degree.project.rcnn.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.mtuci.degree.project.rcnn.registration.RegistrationRequest;
import ru.mtuci.degree.project.rcnn.registration.RegistrationService;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/v1/registration")
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
}
