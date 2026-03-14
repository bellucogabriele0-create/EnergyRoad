package gabrielebelluco.EnergyRoad.controllers;

import gabrielebelluco.EnergyRoad.entities.User;
import gabrielebelluco.EnergyRoad.enums.OrderStatus;
import gabrielebelluco.EnergyRoad.payloads.request.CreateOrderRequestDTO;
import gabrielebelluco.EnergyRoad.payloads.request.OrderDTO;
import gabrielebelluco.EnergyRoad.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
@PreAuthorize("hasAnyAuthority('ADMIN','FOUNDER','INVESTOR','USER')")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO createOrder(
            @RequestBody @Valid CreateOrderRequestDTO dto,
            @AuthenticationPrincipal User currentUser
    ) {
        return orderService.createOrder(dto, currentUser.getUserId());
    }

    @GetMapping("/user")
    public List<OrderDTO> getUserOrders(@AuthenticationPrincipal User currentUser) {
        return orderService.getUserOrders(currentUser.getUserId());
    }

    @PatchMapping("/{orderId}/status")
    @PreAuthorize("hasAnyAuthority('ADMIN','FOUNDER')")
    public OrderDTO updateOrderStatus(
            @PathVariable UUID orderId,
            @RequestParam OrderStatus status
    ) {
        return orderService.updateOrderStatus(orderId, status);
    }
}