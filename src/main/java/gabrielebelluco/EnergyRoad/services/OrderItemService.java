package gabrielebelluco.EnergyRoad.services;

import gabrielebelluco.EnergyRoad.entities.OrderItem;
import gabrielebelluco.EnergyRoad.exceptions.NotFoundException;
import gabrielebelluco.EnergyRoad.repositories.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public OrderItem save(OrderItem item) {
        return orderItemRepository.save(item);
    }

    public void delete(OrderItem item) {
        orderItemRepository.delete(item);
    }

    public OrderItem getById(UUID id) {
        return orderItemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("orderItem non trovato con id: " + id));
    }
}
