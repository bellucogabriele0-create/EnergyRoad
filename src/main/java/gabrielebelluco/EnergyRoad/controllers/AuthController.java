package gabrielebelluco.EnergyRoad.controllers;

import gabrielebelluco.EnergyRoad.payloads.LoginDTO;
import gabrielebelluco.EnergyRoad.payloads.UserCreateDTO;
import gabrielebelluco.EnergyRoad.services.AuthService;
import gabrielebelluco.EnergyRoad.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public String register(@RequestBody @Valid UserCreateDTO payload) {
        userService.save(payload);
        return "Registrazione avvenuta con successo";
    }

    @PostMapping("/login")
    public String login(@RequestBody @Valid LoginDTO payload) {
        return authService.login(payload);
    }
}
