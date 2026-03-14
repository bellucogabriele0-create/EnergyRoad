package gabrielebelluco.EnergyRoad.payloads.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record InvestmentCreateDTO(
        @NotNull(message = "devi specificare il sito su cui investire")
        UUID energySiteId,
        @NotNull(message = "inserisci un importo")
        @Positive(message = "l'importo deve essere maggiore di 0 ")
        Integer amount
) {
}
