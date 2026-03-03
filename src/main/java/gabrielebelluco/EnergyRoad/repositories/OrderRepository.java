package gabrielebelluco.EnergyRoad.repositories;

import gabrielebelluco.EnergyRoad.enums.OrderStatus;
import gabrielebelluco.EnergyRoad.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    boolean existsById(UUID id);

    boolean existsByOrderDate(LocalDate orderDate);

    List<Order> findByOrderStatus(OrderStatus orderStatus);

    List<Order> findByUserUserId(UUID userId);

}
