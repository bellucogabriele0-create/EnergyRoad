package gabrielebelluco.EnergyRoad.Repositories;

import gabrielebelluco.EnergyRoad.entities.EnergySite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EnergySiteRepository extends JpaRepository<EnergySite, UUID> {
}
