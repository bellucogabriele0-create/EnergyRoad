package gabrielebelluco.EnergyRoad.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID orderItemId;
    private int quantity;
    @Column(nullable = false, scale = 2)
    private BigDecimal itemPriceAtPurchase;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductAndService productAndService;

    public OrderItem() {
    }

    public OrderItem(int quantity, BigDecimal itemPriceAtPurchase, Order order, ProductAndService productAndService) {
        this.quantity = quantity;
        this.itemPriceAtPurchase = itemPriceAtPurchase;
        this.order = order;
        this.productAndService = productAndService;
    }


    public UUID getOrderItemId() {
        return orderItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getItemPriceAtPurchase() {
        return itemPriceAtPurchase;
    }

    public void setItemPriceAtPurchase(BigDecimal itemPriceAtPurchase) {
        this.itemPriceAtPurchase = itemPriceAtPurchase;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public ProductAndService getProductAndService() {
        return productAndService;
    }

    public void setProductAndService(ProductAndService productAndService) {
        this.productAndService = productAndService;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderItemId=" + orderItemId +
                ", quantity=" + quantity +
                ", itemPriceAtPurchase=" + itemPriceAtPurchase +
                ", order=" + order +
                ", productAndService=" + productAndService +
                '}';
    }
}
