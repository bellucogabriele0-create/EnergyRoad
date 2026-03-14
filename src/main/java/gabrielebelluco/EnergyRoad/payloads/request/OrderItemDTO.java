package gabrielebelluco.EnergyRoad.payloads.request;

import gabrielebelluco.EnergyRoad.entities.OrderItem;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderItemDTO(
        UUID orderItemId,
        UUID productId,
        @NotNull
        int quantity,
        @NotNull
        BigDecimal itemPriceAtPurchase) {
    public static OrderItemDTO from(OrderItem item) {
        return new OrderItemDTO(
                item.getOrderItemId(),
                item.getProductAndService().getProductAndServiceId(),
                item.getQuantity(),
                item.getItemPriceAtPurchase()
        );
    }
}
