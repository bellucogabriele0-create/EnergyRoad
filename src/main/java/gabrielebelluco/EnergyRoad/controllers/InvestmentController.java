package gabrielebelluco.EnergyRoad.controllers;

import gabrielebelluco.EnergyRoad.entities.Investment;
import gabrielebelluco.EnergyRoad.enums.InvestmentStatus;
import gabrielebelluco.EnergyRoad.payloads.InvestmentCreateDTO;
import gabrielebelluco.EnergyRoad.services.InvestmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
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
    @PreAuthorize("hasAnyAuthority('FOUNDER', 'ADMIN', 'INVESTOR')")
    public List<Investment> getUserInvestments(@PathVariable UUID userId) {
        return investmentService.getUserInvestments(userId);
    }

    @GetMapping("/site/{energySiteId}")
    @PreAuthorize("hasAnyAuthority('FOUNDER', 'ADMIN', 'INVESTOR')")
    public List<Investment> getEnergySiteInvestments(@PathVariable UUID energySiteId) {
        return investmentService.getEnergySiteInvestments(energySiteId);
    }

    @PatchMapping("/{investmentId}/status")
    @PreAuthorize("hasAuthority('ADMIN', 'FOUNDER')")
    public Investment updateStatus(
            @PathVariable UUID investmentId,
            @RequestParam InvestmentStatus status
    ) {
        return investmentService.updateStatus(investmentId, status);
    }

    @PostMapping("/{userId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'FOUNDER', 'INVESTOR')")
    @ResponseStatus(HttpStatus.CREATED)
    public Investment createInvestment(
            @PathVariable UUID userId,
            @RequestBody @Valid InvestmentCreateDTO dto,
            BindingResult validateResult
    ) {
        return investmentService.createInvestment(userId, dto);
    }

    @GetMapping("/{userId}/sites/{siteId}/total")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'FOUNDER', 'INVESTOR')")
    public int getUserInvestmentInSite(@PathVariable UUID userId, @PathVariable UUID siteId) {
        return investmentService.getUserInvestmentInSite(userId, siteId);
    }
}
