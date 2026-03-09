package gabrielebelluco.EnergyRoad.services;

import gabrielebelluco.EnergyRoad.entities.EnergySite;
import gabrielebelluco.EnergyRoad.entities.Investment;
import gabrielebelluco.EnergyRoad.entities.User;
import gabrielebelluco.EnergyRoad.enums.InvestmentStatus;
import gabrielebelluco.EnergyRoad.exceptions.NotFoundException;
import gabrielebelluco.EnergyRoad.payloads.InvestmentCreateDTO;
import gabrielebelluco.EnergyRoad.repositories.InvestmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class InvestmentService {
    private final InvestmentRepository investmentRepository;
    private final EnergySiteService energySiteService;
    private final UserService userService;

    public InvestmentService(InvestmentRepository investmentRepository, EnergySiteService energySiteService, UserService userService) {
        this.investmentRepository = investmentRepository;
        this.energySiteService = energySiteService;
        this.userService = userService;
    }

    public Investment createInvestment(UUID userId, InvestmentCreateDTO dto) {
        User user = userService.getById(userId);
        EnergySite site = energySiteService.getById(dto.energySiteId());
        if (site.getTotalInvested() + dto.amount() > site.getTargetAmount()) {
            throw new IllegalArgumentException(
                    "non puoi investire: supereresti la soglia massima di " + site.getTargetAmount()
            );
        }
        Investment investment = new Investment();
        investment.setUser(user);
        investment.setEnergySite(site);
        investment.setAmount(dto.amount());
        investment.setStatus(InvestmentStatus.ATTIVO);
        investment.setInvestmentDate(LocalDateTime.now());
        site.addInvestment(dto.amount());
        if (site.getTotalInvested() >= site.getTargetAmount()) {
            investment.setStatus(InvestmentStatus.COMPLETTATO);
            throw new IllegalArgumentException("questo sito ha già raggiunto il target di investimento");

        }
        energySiteService.save(site);
        return investmentRepository.save(investment);
    }

    public List<Investment> getUserInvestments(UUID userId) {
        User user = userService.getById(userId);
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
        User user = userService.getById(userId);
        EnergySite site = energySiteService.getById(energySiteId);

        return investmentRepository.findByUserAndEnergySite(user, site)
                .stream()
                .mapToInt(Investment::getAmount)
                .sum();
    }
}
