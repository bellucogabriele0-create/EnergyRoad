package gabrielebelluco.EnergyRoad.payloads.request;

import gabrielebelluco.EnergyRoad.enums.RoleType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AssignRoleRequestDTO(
        @NotBlank(message = "email obbligatoria")
        String email,
        @NotNull(message = "scegli di promuovere ad admin o founder")
        RoleType roleType) {
}

