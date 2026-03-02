package gabrielebelluco.EnergyRoad.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductCategory extends JpaRepository<ProductCategory, UUID> {
}
