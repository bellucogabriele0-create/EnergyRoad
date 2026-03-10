package gabrielebelluco.EnergyRoad.controllers;

import gabrielebelluco.EnergyRoad.entities.User;
import gabrielebelluco.EnergyRoad.enums.InvestmentStatus;
import gabrielebelluco.EnergyRoad.payloads.InvestmentCreateDTO;
import gabrielebelluco.EnergyRoad.payloads.InvestmentResponseDTO;
import gabrielebelluco.EnergyRoad.services.InvestmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public List<InvestmentResponseDTO> getUserInvestments(@PathVariable UUID userId) {
        return investmentService.getUserInvestments(userId).stream()
                .map(InvestmentResponseDTO::from)
                .toList();
    }

    @GetMapping("/site/{energySiteId}")
    public List<InvestmentResponseDTO> getEnergySiteInvestments(@PathVariable UUID energySiteId) {
        return investmentService.getEnergySiteInvestments(energySiteId).stream()
                .map(InvestmentResponseDTO::from)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InvestmentResponseDTO createInvestment(
            @RequestBody @Valid InvestmentCreateDTO dto,
            @AuthenticationPrincipal User currentUser
    ) {
        return InvestmentResponseDTO.from(investmentService.createInvestment(currentUser.getUserId(), dto));
    }

    @PatchMapping("/{investmentId}/status")
    @PreAuthorize("hasAnyAuthority('ADMIN','FOUNDER')")
    public InvestmentResponseDTO updateStatus(
            @PathVariable UUID investmentId,
            @RequestParam InvestmentStatus status
    ) {
        return InvestmentResponseDTO.from(investmentService.updateStatus(investmentId, status));
    }

    @GetMapping("/{userId}/sites/{siteId}/total")
    public int getUserInvestmentInSite(@PathVariable UUID userId, @PathVariable UUID siteId) {
        return investmentService.getUserInvestmentInSite(userId, siteId);
    }
}