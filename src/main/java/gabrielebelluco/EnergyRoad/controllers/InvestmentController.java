package gabrielebelluco.EnergyRoad.controllers;

import gabrielebelluco.EnergyRoad.entities.Investment;
import gabrielebelluco.EnergyRoad.enums.InvestmentStatus;
import gabrielebelluco.EnergyRoad.payloads.InvestmentCreateDTO;
import gabrielebelluco.EnergyRoad.payloads.InvestmentResponseDTO;
import gabrielebelluco.EnergyRoad.services.InvestmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/investments")
@PreAuthorize("hasAnyAuthority('INVESTOR','FOUNDER','ADMIN')")
public class InvestmentController {

    private final InvestmentService investmentService;

    public InvestmentController(InvestmentService investmentService) {
        this.investmentService = investmentService;
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAnyAuthority('FOUNDER','ADMIN','INVESTOR')")
    public List<InvestmentResponseDTO> getUserInvestments(@PathVariable UUID userId) {
        return investmentService.getUserInvestments(userId)
                .stream()
                .map(i -> new InvestmentResponseDTO(
                        i.getInvestmentId(),
                        i.getAmount(),
                        i.getStatus(),
                        i.getEnergySite().getEnergySiteId()
                ))
                .toList();
    }

    @GetMapping("/site/{energySiteId}")
    @PreAuthorize("hasAnyAuthority('FOUNDER', 'ADMIN', 'INVESTOR')")
    public List<InvestmentResponseDTO> getEnergySiteInvestments(@PathVariable UUID energySiteId) {
        return investmentService.getEnergySiteInvestments(energySiteId)
                .stream()
                .map(i -> new InvestmentResponseDTO(
                        i.getInvestmentId(),
                        i.getAmount(),
                        i.getStatus(),
                        i.getEnergySite().getEnergySiteId()
                ))
                .toList();
    }

    @PatchMapping("/{investmentId}/status")
    @PreAuthorize("hasAnyAuthority('ADMIN','FOUNDER')")
    public InvestmentResponseDTO updateStatus(
            @PathVariable UUID investmentId,
            @RequestParam InvestmentStatus status
    ) {
        var inv = investmentService.updateStatus(investmentId, status);
        return new InvestmentResponseDTO(
                inv.getInvestmentId(),
                inv.getAmount(),
                inv.getStatus(),
                inv.getEnergySite().getEnergySiteId()
        );
    }

    @PostMapping("/{userId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'FOUNDER', 'INVESTOR')")
    @ResponseStatus(HttpStatus.CREATED)
    public InvestmentResponseDTO createInvestment(
            @PathVariable UUID userId,
            @RequestBody @Valid InvestmentCreateDTO dto
    ) {
        Investment investment = investmentService.createInvestment(userId, dto);

        return new InvestmentResponseDTO(
                investment.getInvestmentId(),
                investment.getAmount(),
                investment.getStatus(),
                investment.getEnergySite().getEnergySiteId()
        );
    }

    @GetMapping("/{userId}/sites/{siteId}/total")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'FOUNDER', 'INVESTOR')")
    public int getUserInvestmentInSite(@PathVariable UUID userId, @PathVariable UUID siteId) {
        return investmentService.getUserInvestmentInSite(userId, siteId);
    }
}