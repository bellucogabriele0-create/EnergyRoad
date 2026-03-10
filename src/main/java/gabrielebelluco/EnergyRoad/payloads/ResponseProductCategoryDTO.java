package gabrielebelluco.EnergyRoad.payloads;

import java.util.UUID;

public record ResponseProductCategoryDTO(UUID productCategoryId,
                                         String name) {
}
