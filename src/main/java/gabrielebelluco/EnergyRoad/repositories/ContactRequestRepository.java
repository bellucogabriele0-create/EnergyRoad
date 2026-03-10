package gabrielebelluco.EnergyRoad.repositories;

import gabrielebelluco.EnergyRoad.entities.ContactRequest;
import gabrielebelluco.EnergyRoad.enums.ContactRequestType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ContactRequestRepository extends JpaRepository<ContactRequest, UUID> {

    boolean existsByContactRequestId(UUID id);

    List<ContactRequest> findByContactRequestName(String contactRequestName);

    List<ContactRequest> findByContactRequestType(ContactRequestType contactRequestType);

    List<ContactRequest> findAllByOrderByCreatedAtDesc();

    List<ContactRequest> findAllByOrderByCreatedAtAsc();
}
