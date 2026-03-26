package gabrielebelluco.EnergyRoad.repositories;

import gabrielebelluco.EnergyRoad.entities.EnergySite;
import gabrielebelluco.EnergyRoad.entities.Investment;
import gabrielebelluco.EnergyRoad.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface InvestmentRepository extends JpaRepository<Investment, UUID> {
    List<Investment> findByUser(User user);

    List<Investment> findByEnergySite(EnergySite energySite);


    Optional<Investment> findByUserAndEnergySite(User user, EnergySite energySite);
}
