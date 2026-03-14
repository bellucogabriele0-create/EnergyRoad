package gabrielebelluco.EnergyRoad.payloads.response;

import java.util.UUID;

public record ResponseProductCategoryDTO(UUID productCategoryId,
                                         String name) {
}
