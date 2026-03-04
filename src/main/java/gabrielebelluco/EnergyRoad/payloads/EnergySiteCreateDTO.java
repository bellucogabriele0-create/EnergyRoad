package gabrielebelluco.EnergyRoad.payloads;

import gabrielebelluco.EnergyRoad.enums.EnergySiteType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EnergySiteCreateDTO(
        @NotBlank(message = "dai un nome a questo sito")
        String name,
        @NotBlank(message = "dai una descrizione a questo sito, massimo 500 caratteri")
        @Size(max = 500)
        String description,
        @NotNull(message = "dai una latitudine al sito ")
        Double latitude,
        @NotNull(message = "dai una longitudine al sito ")
        Double longitude,
        @NotNull(message = "scgli un tipo di sito")
        EnergySiteType energySiteType) {
}
