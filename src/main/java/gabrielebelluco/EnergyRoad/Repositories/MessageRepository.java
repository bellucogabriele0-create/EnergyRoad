package gabrielebelluco.EnergyRoad.Repositories;

import gabrielebelluco.EnergyRoad.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<Message, UUID> {
}
