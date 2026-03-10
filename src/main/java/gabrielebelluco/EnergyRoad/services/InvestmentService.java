package gabrielebelluco.EnergyRoad.services;

import gabrielebelluco.EnergyRoad.entities.EnergySite;
import gabrielebelluco.EnergyRoad.entities.Investment;
import gabrielebelluco.EnergyRoad.entities.User;
import gabrielebelluco.EnergyRoad.enums.InvestmentStatus;
import gabrielebelluco.EnergyRoad.exceptions.NotFoundException;
import gabrielebelluco.EnergyRoad.payloads.InvestmentCreateDTO;
import gabrielebelluco.EnergyRoad.payloads.InvestmentResponseDTO;
import gabrielebelluco.EnergyRoad.repositories.InvestmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class InvestmentService {
    private final InvestmentRepository investmentRepository;
    private final EnergySiteService energySiteService;
    private final UserService userService;
    private final PaymentService paymentService;

    public InvestmentService(InvestmentRepository investmentRepository, EnergySiteService energySiteService, UserService userService, PaymentService paymentService) {
        this.investmentRepository = investmentRepository;
        this.energySiteService = energySiteService;
        this.userService = userService;
        this.paymentService = paymentService;
    }

    public Investment createInvestment(UUID userId, InvestmentCreateDTO dto) {
        User user = userService.findById(userId);
        EnergySite site = energySiteService.getById(dto.energySiteId());
        if (site.getTotalInvested() >= site.getTargetAmount()) {
            throw new IllegalArgumentException("questo sito ha già raggiunto il target di investimento");
        }
        if (site.getTotalInvested() + dto.amount().intValue() > site.getTargetAmount()) {
            throw new IllegalArgumentException(
                    "non puoi investire supereresti la soglia massima di: " + site.getTargetAmount()
            );
        }
        Investment investment = new Investment();
        investment.setUser(user);
        investment.setEnergySite(site);
        investment.setAmount(dto.amount());
        investment.setInvestmentDate(LocalDateTime.now());
        site.addInvestment(dto.amount().intValue());
        energySiteService.save(site);
        investment.setStatus(
                site.getTotalInvested() >= site.getTargetAmount()
                        ? InvestmentStatus.COMPLETATO
                        : InvestmentStatus.ATTIVO
        );
        return investmentRepository.save(investment);
    }

    public InvestmentResponseDTO payInvestment(UUID investmentId, String paymentMethodNonce) {
        Investment investment = investmentRepository.findById(investmentId)
                .orElseThrow(() -> new NotFoundException("investimento non trovato"));
        if (investment.getStatus() != InvestmentStatus.ATTIVO) {
            throw new IllegalArgumentException("questo investimento non è attivo");
        }
        paymentService.processPayment(
                paymentMethodNonce,
                BigDecimal.valueOf(investment.getAmount())
        );
        investment.setStatus(InvestmentStatus.COMPLETATO);
        return InvestmentResponseDTO.from(investmentRepository.save(investment));
    }

    public List<Investment> getUserInvestments(UUID userId) {
        User user = userService.findById(userId);
        return investmentRepository.findByUser(user);
    }

    public List<Investment> getEnergySiteInvestments(UUID energySiteId) {
        EnergySite site = energySiteService.getById(energySiteId);
        return investmentRepository.findByEnergySite(site);
    }

    public Investment updateStatus(UUID investmentId, InvestmentStatus status) {
        Investment investment = investmentRepository.findById(investmentId)
                .orElseThrow(() -> new NotFoundException("investimento non trovato"));
        if (investment.getStatus() == status) {
            throw new IllegalArgumentException("questo investimento è già: " + status);
        }
        investment.setStatus(status);
        return investmentRepository.save(investment);
    }

    public int getUserInvestmentInSite(UUID userId, UUID energySiteId) {
        User user = userService.findById(userId);
        EnergySite site = energySiteService.getById(energySiteId);

        return investmentRepository.findByUserAndEnergySite(user, site)
                .stream()
                .mapToInt(Investment::getAmount)
                .sum();
    }
}
