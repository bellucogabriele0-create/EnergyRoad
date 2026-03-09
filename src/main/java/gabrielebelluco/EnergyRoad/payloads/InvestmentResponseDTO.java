package gabrielebelluco.EnergyRoad.payloads;

import gabrielebelluco.EnergyRoad.enums.InvestmentStatus;

import java.util.UUID;

public record InvestmentResponseDTO(UUID investmentId,
                                    int amount,
                                    InvestmentStatus status,
                                    UUID energySiteId) {
}
