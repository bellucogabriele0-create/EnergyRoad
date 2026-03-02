package gabrielebelluco.EnergyRoad.Repositories;

import gabrielebelluco.EnergyRoad.entities.ProductAndService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductAndServiceRepository extends JpaRepository<ProductAndService, UUID> {
}
