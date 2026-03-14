package gabrielebelluco.EnergyRoad.payloads.request;

import gabrielebelluco.EnergyRoad.enums.EnergySiteStatus;
import jakarta.validation.constraints.NotNull;

public record EnergySiteStatusDTO(
        @NotNull(message = "devi scegliere un tipo di stato tra in sviluppo, funzionante o non funzionante")
        EnergySiteStatus status
) {
}

