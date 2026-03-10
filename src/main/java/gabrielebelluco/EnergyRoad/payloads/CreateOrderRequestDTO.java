package gabrielebelluco.EnergyRoad.payloads;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateOrderRequestDTO(
        @NotNull
        List<CreateOrderItemDTO> items
) {
}
