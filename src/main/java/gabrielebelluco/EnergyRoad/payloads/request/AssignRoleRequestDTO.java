package gabrielebelluco.EnergyRoad.payloads.request;

import gabrielebelluco.EnergyRoad.enums.RoleType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AssignRoleRequestDTO {
    @NotBlank(message = "email obbligatoria")
    private String email;
    @NotNull(message = "scegli di promuovere ad admin o founder")
    private RoleType roleType;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }
}
