package gabrielebelluco.EnergyRoad.repositories;

import gabrielebelluco.EnergyRoad.entities.ProductAndService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface ProductAndServiceRepository extends JpaRepository<ProductAndService, UUID> {
    boolean existsById(UUID uuid);

    Optional<ProductAndService> findById(UUID Id);


}
