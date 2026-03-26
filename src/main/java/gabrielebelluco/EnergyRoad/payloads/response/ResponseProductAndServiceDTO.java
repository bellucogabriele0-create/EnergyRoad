package gabrielebelluco.EnergyRoad.payloads.response;

import java.math.BigDecimal;
import java.util.UUID;

public record ResponseProductAndServiceDTO(
        UUID productAndServiceId,
        String name,
        String description,
        BigDecimal price,
        UUID categoryId
) {
}
