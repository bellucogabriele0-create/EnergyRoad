package gabrielebelluco.EnergyRoad.Repositories;

import gabrielebelluco.EnergyRoad.entities.ContactRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface ContactRequestRepository extends JpaRepository<ContactRequest, UUID> {

    boolean existsByContactRequestId(UUID id);

    List<ContactRequest> findByContactRequestName(String contactRequestName);

    List<ContactRequest> findByContactRequestType(String contactRequestType);

    List<ContactRequest> findByContactRequestCreatedAtDesc(LocalDate contactRequestCreatedAt);

    List<ContactRequest> findByContactRequestCreatedAtAsc(LocalDate contactRequestCreatedAt);
}
