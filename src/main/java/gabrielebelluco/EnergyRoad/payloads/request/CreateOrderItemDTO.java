package gabrielebelluco.EnergyRoad.payloads.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateOrderItemDTO(
        @NotNull(message = "inserisci un prodotto")
        UUID productId,
        @Min(value = 1, message = "la quantità deve essere almeno 1")
        int quantity
) {
}
