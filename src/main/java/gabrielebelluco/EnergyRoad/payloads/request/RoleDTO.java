package gabrielebelluco.EnergyRoad.payloads.request;

import gabrielebelluco.EnergyRoad.enums.RoleType;
import jakarta.validation.constraints.NotNull;

public record RoleDTO(@NotNull(message = "la scelta è obbligatoria")
                      RoleType roleType) {
}
