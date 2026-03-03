package gabrielebelluco.EnergyRoad.payloads;

import gabrielebelluco.EnergyRoad.enums.RoleType;
import jakarta.validation.constraints.NotBlank;

public class RoleDTO {
    @NotBlank(message = "la scelta è obbligatoria")
    private RoleType roleType;

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }
}
