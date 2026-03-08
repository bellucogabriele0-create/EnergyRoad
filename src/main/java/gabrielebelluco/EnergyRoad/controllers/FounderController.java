package gabrielebelluco.EnergyRoad.controllers;

import gabrielebelluco.EnergyRoad.entities.User;
import gabrielebelluco.EnergyRoad.payloads.AssignRoleRequestDTO;
import gabrielebelluco.EnergyRoad.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAnyAuthority('FOUNDER')")
    @PutMapping("/assign-role")
    public String assignRole(@RequestBody AssignRoleRequestDTO dto) {
        User actionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User updatedUser = userService.assignRoleToUser(dto.email(), dto.roleType(), actionUser);
        return "il ruolo " + dto.roleType() + " è stato assegnato a " + updatedUser.getEmail();
    }
}

