package gabrielebelluco.EnergyRoad.Repositories;

import gabrielebelluco.EnergyRoad.ENUMS.EnergySiteStatus;
import gabrielebelluco.EnergyRoad.ENUMS.EnergySiteType;
import gabrielebelluco.EnergyRoad.entities.EnergySite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EnergySiteRepository extends JpaRepository<EnergySite, UUID> {
    boolean existsById(UUID id);

    Optional<EnergySite> findById(UUID id);

    boolean existsByName(String name);

    List<EnergySite> findAllByOrderByCreatedAtDesc(LocalDateTime createdAt);

    List<EnergySite> findByStatus(EnergySiteStatus status);

    List<EnergySite> findByType(EnergySiteType type);
}
