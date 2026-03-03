package gabrielebelluco.EnergyRoad.payloads;

import gabrielebelluco.EnergyRoad.enums.RoleType;
import jakarta.validation.constraints.NotNull;

public class RoleDTO {
    @NotNull(message = "la scelta è obbligatoria")
    private RoleType roleType;

    public RoleDTO(RoleType roleType) {
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }
}
