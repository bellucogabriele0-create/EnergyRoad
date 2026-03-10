package gabrielebelluco.EnergyRoad.payloads;

import gabrielebelluco.EnergyRoad.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record OrderDTO(
        UUID orderId,
        LocalDate orderDate,
        BigDecimal totalAmount,
        OrderStatus orderStatus,
        UUID userId,
        List<OrderItemDTO> items
) {
}
