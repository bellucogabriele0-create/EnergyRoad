package gabrielebelluco.EnergyRoad.payloads.response;

import gabrielebelluco.EnergyRoad.entities.Investment;
import gabrielebelluco.EnergyRoad.enums.InvestmentStatus;

import java.util.UUID;

public record InvestmentResponseDTO(UUID investmentId,
                                    int amount,
                                    InvestmentStatus status,
                                    UUID energySiteId) {
    public static InvestmentResponseDTO from(Investment investment) {
        return new InvestmentResponseDTO(
                investment.getInvestmentId(),
                investment.getAmount(),
                investment.getStatus(),
                investment.getEnergySite().getEnergySiteId()
        );
    }
}
