package gabrielebelluco.EnergyRoad.Repositories;

import gabrielebelluco.EnergyRoad.ENUMS.OrderStatus;
import gabrielebelluco.EnergyRoad.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    boolean existsByOrderId(UUID id);

    boolean existsByOrderDate(LocalDate orderDate);

    Optional<Order> findByOrderStatus(OrderStatus orderStatus); //TODO da sfruttare per il login
}
