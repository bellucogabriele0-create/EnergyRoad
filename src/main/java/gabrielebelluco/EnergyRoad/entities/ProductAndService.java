package gabrielebelluco.EnergyRoad.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "product_and_services")
public class ProductAndService {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID productAndServiceId;
    private String name;
    private String description;
    @Column(nullable = false, scale = 2)
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private ProductCategory category;

    public ProductAndService() {
    }

    public ProductAndService(String name, String description, BigDecimal price, ProductCategory category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }


    public UUID getProductAndServiceId() {
        return productAndServiceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ProductAndService{" +
                "productAndServiceId=" + productAndServiceId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", category=" + category +
                '}';
    }
}
