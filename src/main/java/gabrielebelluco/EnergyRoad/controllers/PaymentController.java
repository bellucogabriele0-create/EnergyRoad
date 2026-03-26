package gabrielebelluco.EnergyRoad.controllers;

import gabrielebelluco.EnergyRoad.payloads.request.OrderDTO;
import gabrielebelluco.EnergyRoad.payloads.request.PayInvestmentDTO;
import gabrielebelluco.EnergyRoad.payloads.request.PayOrderDTO;
import gabrielebelluco.EnergyRoad.payloads.response.InvestmentResponseDTO;
import gabrielebelluco.EnergyRoad.services.InvestmentService;
import gabrielebelluco.EnergyRoad.services.OrderService;
import gabrielebelluco.EnergyRoad.services.PaymentService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/payments")
@PreAuthorize("hasAnyAuthority('USER','INVESTOR','ADMIN','FOUNDER')")
public class PaymentController {

    private final PaymentService paymentService;
    private final OrderService orderService;
    private final InvestmentService investmentService;

    public PaymentController(PaymentService paymentService, OrderService orderService, InvestmentService investmentService) {
        this.paymentService = paymentService;
        this.orderService = orderService;
        this.investmentService = investmentService;
    }

    @GetMapping("/client-token")
    public String getClientToken() {
        return paymentService.generateClientToken();
    }

    @PostMapping("/orders/{orderId}/pay")
    public OrderDTO payOrder(
            @PathVariable UUID orderId,
            @RequestBody @Valid PayOrderDTO dto
    ) {
        return orderService.payOrder(orderId, dto.paymentMethodNonce());
    }

    @PostMapping("/investments/{investmentId}/pay")
    public InvestmentResponseDTO payInvestment(
            @PathVariable UUID investmentId,
            @RequestBody @Valid PayInvestmentDTO dto
    ) {
        return investmentService.payInvestment(investmentId, dto.paymentMethodNonce());
    }
}