package gabrielebelluco.EnergyRoad.controllers;

import gabrielebelluco.EnergyRoad.entities.User;
import gabrielebelluco.EnergyRoad.payloads.request.AssignRoleRequestDTO;
import gabrielebelluco.EnergyRoad.services.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/founder")
public class FounderController {
    private final UserService userService;

    public FounderController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/assign-role")
    public String assignRole(@RequestBody AssignRoleRequestDTO request) {
        User actionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User updatedUser = userService.assignRoleToUser(request.getEmail(), request.getRoleType(), actionUser);
        return "il ruolo " + request.getRoleType() + " è stato assegnato a " + updatedUser.getEmail();
    }
}

