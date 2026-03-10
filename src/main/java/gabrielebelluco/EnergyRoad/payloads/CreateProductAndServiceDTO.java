package gabrielebelluco.EnergyRoad.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateProductAndServiceDTO(
        @NotBlank(message = "inserisci una nome")
        String name,
        @NotBlank(message = "inserisci una descrizione")
        String description,
        @NotNull(message = "devi impostare un prezzo ")
        BigDecimal price,
        @NotNull
        UUID categoryId
) {
}
