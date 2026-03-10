package gabrielebelluco.EnergyRoad.payloads;

import gabrielebelluco.EnergyRoad.entities.EnergySite;
import gabrielebelluco.EnergyRoad.enums.EnergySiteStatus;
import gabrielebelluco.EnergyRoad.enums.EnergySiteType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record EnergySiteResponseDTO(
        UUID energySiteId,
        String name,
        String description,
        BigDecimal latitude,
        BigDecimal longitude,
        int targetAmount,
        int totalInvested,
        EnergySiteType type,
        EnergySiteStatus status,
        String image,
        LocalDateTime createdAt
) {
    public static EnergySiteResponseDTO from(EnergySite site) {
        return new EnergySiteResponseDTO(
                site.getEnergySiteId(),
                site.getName(),
                site.getDescription(),
                site.getLatitude(),
                site.getLongitude(),
                site.getTargetAmount(),
                site.getTotalInvested(),
                site.getType(),
                site.getStatus(),
                site.getImage(),
                site.getCreatedAt()
        );
    }
}
