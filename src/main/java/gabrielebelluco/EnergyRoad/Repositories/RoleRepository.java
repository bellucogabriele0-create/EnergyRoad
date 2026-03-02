package gabrielebelluco.EnergyRoad.Repositories;

import gabrielebelluco.EnergyRoad.ENUMS.RoleType;
import gabrielebelluco.EnergyRoad.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    boolean existsById(UUID id);

    Optional<Role> findByRoleType(RoleType roleType);

    boolean existsByRoleType(RoleType roleType);
}
