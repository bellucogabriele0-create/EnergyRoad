package gabrielebelluco.EnergyRoad.Repositories;

import gabrielebelluco.EnergyRoad.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsById(UUID id);

    Optional<User> findByEmail(String email); //TODO da sfruttare per il login

    boolean existsByEmail(String email); //TODO da sfruttare per verifica di duplicati

    //List<User> findByRoleType(RoleType type);

    List<User> findByFirstnameContainingIgnoreCase(String firstname);

    List<User> findAllByOrderByCreatedAtDesc(LocalDate createdAt);
}
