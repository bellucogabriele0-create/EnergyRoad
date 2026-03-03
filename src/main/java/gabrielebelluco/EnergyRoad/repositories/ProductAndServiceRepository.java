package gabrielebelluco.EnergyRoad.repositories;

import gabrielebelluco.EnergyRoad.entities.ProductAndService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface ProductAndServiceRepository extends JpaRepository<ProductAndService, UUID> {
    boolean existsById(UUID uuid);

    Optional<ProductAndService> findById(UUID Id);

    boolean existsByName(String name);


    List<ProductAndService> findByDescription(String description);

    List<ProductAndService> findByPrice(double price);

    Optional<ProductAndService> findByName(String name);

    List<ProductAndService> findByPriceLessThanEqual(Double maxPrice);

    List<ProductAndService> findByPriceGreaterThanEqual(Double minPrice);
}
