package gabrielebelluco.EnergyRoad.repositories;

import gabrielebelluco.EnergyRoad.entities.EnergySite;
import gabrielebelluco.EnergyRoad.enums.EnergySiteStatus;
import gabrielebelluco.EnergyRoad.enums.EnergySiteType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EnergySiteRepository extends JpaRepository<EnergySite, UUID> {
    boolean existsById(UUID id);

    Optional<EnergySite> findById(UUID id);

    boolean existsByName(String name);

    boolean existsByLatitudeAndLongitude(BigDecimal latitude, BigDecimal longitude);

    List<EnergySite> findAllByOrderByCreatedAtDesc();

    List<EnergySite> findByStatus(EnergySiteStatus status);

    List<EnergySite> findByType(EnergySiteType type);

}
