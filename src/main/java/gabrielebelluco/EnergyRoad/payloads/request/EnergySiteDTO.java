package gabrielebelluco.EnergyRoad.payloads.request;

import gabrielebelluco.EnergyRoad.enums.EnergySiteType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record EnergySiteDTO(
        @NotBlank(message = "dai un nome a questo sito")
        String name,
        @NotBlank(message = "dai una descrizione a questo sito, massimo 500 caratteri")
        @Size(max = 500)
        String description,
        @NotNull(message = "dai una latitudine al sito ")
        BigDecimal latitude,
        @NotNull(message = "dai una longitudine al sito ")
        BigDecimal longitude,
        @Positive(message = "devi inserire un target da raggiungere che sia maggiore di zero")
        int targetAmount,
        @NotNull(message = "scgli un tipo di sito")
        EnergySiteType energySiteType,
        String image
) {
}
