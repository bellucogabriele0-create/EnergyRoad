package gabrielebelluco.EnergyRoad.Repositories;

import gabrielebelluco.EnergyRoad.entities.ProductAndService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductAndServiceRepository extends JpaRepository<ProductAndService, UUID> {
    boolean productAndServiceId(UUID id);

    boolean existsByName(String name);

    List<ProductAndService> findByCategoryProductCategoryId(UUID categoryId);

    List<ProductAndService> findByCategoryProductDescription(String description);

    List<ProductAndService> findByCategoryProductPrice(double price);
}
