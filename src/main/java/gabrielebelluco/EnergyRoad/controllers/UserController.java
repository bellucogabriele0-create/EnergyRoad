package gabrielebelluco.EnergyRoad.controllers;

import gabrielebelluco.EnergyRoad.entities.User;
import gabrielebelluco.EnergyRoad.enums.RoleType;
import gabrielebelluco.EnergyRoad.payloads.request.UserCreateDTO;
import gabrielebelluco.EnergyRoad.payloads.response.UserResponseDTO;
import gabrielebelluco.EnergyRoad.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','FOUNDER')")
    public UserResponseDTO getById(@PathVariable UUID id) {
        return UserResponseDTO.from(userService.findById(id));
    }

    @GetMapping("/email/{email}")
    @PreAuthorize("hasAnyAuthority('ADMIN','FOUNDER')")
    public UserResponseDTO getByEmail(@PathVariable String email) {
        return UserResponseDTO.from(userService.findByEmail(email));
    }

    @GetMapping("/me")
    public UserResponseDTO getCurrentUser(@AuthenticationPrincipal User currentUser) {
        return UserResponseDTO.from(currentUser);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('FOUNDER')")
    public List<UserResponseDTO> getAllUsers() {
        return userService.findAll().stream()
                .map(UserResponseDTO::from)
                .toList();
    }

    @PostMapping("/create-investor")
    @PreAuthorize("hasAnyAuthority('ADMIN','FOUNDER','USER')")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO createInvestor(@RequestBody @Valid UserCreateDTO payload) {
        return UserResponseDTO.from(userService.createUser(payload, RoleType.INVESTOR));
    }
}
