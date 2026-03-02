package gabrielebelluco.EnergyRoad.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "product_categories")
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID productCategoryId;
    private String name;

    public ProductCategory() {
    }

    public ProductCategory(UUID productCategoryId, String name) {
        this.productCategoryId = productCategoryId;
        this.name = name;
    }

    public UUID getProductCategoryId() {
        return productCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "productCategoryId=" + productCategoryId +
                ", name='" + name + '\'' +
                '}';
    }
}
