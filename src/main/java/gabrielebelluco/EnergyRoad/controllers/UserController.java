package gabrielebelluco.EnergyRoad.controllers;

import gabrielebelluco.EnergyRoad.entities.User;
import gabrielebelluco.EnergyRoad.services.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable UUID id) {
        return userService.getById(id);
    }

    @GetMapping("/email/{email}")
    public User getByEmail(@PathVariable String email) {
        return userService.findByEmail(email);
    }

    @GetMapping("/me")
    public User getCurrentUser() {
        return (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

}
