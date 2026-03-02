package gabrielebelluco.EnergyRoad.Repositories;

import gabrielebelluco.EnergyRoad.entities.Investment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InvestmentRepository extends JpaRepository<Investment, UUID> {
}
