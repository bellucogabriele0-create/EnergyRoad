package gabrielebelluco.EnergyRoad.controllers;

import gabrielebelluco.EnergyRoad.enums.RoleType;
import gabrielebelluco.EnergyRoad.payloads.request.LoginDTO;
import gabrielebelluco.EnergyRoad.payloads.request.UserCreateDTO;
import gabrielebelluco.EnergyRoad.payloads.response.UserResponseDTO;
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
    public UserResponseDTO register(@RequestBody @Valid UserCreateDTO payload) {
        return UserResponseDTO.from(userService.createUser(payload, RoleType.USER));
    }

    @PostMapping("/login")
    public String login(@RequestBody @Valid LoginDTO payload) {
        return authService.login(payload);
    }

    @PostMapping("/register-investor")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO registerInvestor(@RequestBody @Valid UserCreateDTO payload) {
        return UserResponseDTO.from(userService.createUser(payload, RoleType.INVESTOR));
    }
}