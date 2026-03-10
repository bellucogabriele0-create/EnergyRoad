package gabrielebelluco.EnergyRoad.services;

import gabrielebelluco.EnergyRoad.entities.Order;
import gabrielebelluco.EnergyRoad.entities.OrderItem;
import gabrielebelluco.EnergyRoad.entities.ProductAndService;
import gabrielebelluco.EnergyRoad.entities.User;
import gabrielebelluco.EnergyRoad.enums.OrderStatus;
import gabrielebelluco.EnergyRoad.exceptions.NotFoundException;
import gabrielebelluco.EnergyRoad.payloads.CreateOrderItemDTO;
import gabrielebelluco.EnergyRoad.payloads.CreateOrderRequestDTO;
import gabrielebelluco.EnergyRoad.payloads.OrderDTO;
import gabrielebelluco.EnergyRoad.payloads.OrderItemDTO;
import gabrielebelluco.EnergyRoad.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Transactional
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ProductAndServiceService productService;
    private final OrderItemService orderItemService;
    private final PaymentService paymentService;

    public OrderService(OrderRepository orderRepository, UserService userService,
                        ProductAndServiceService productService, OrderItemService orderItemService, PaymentService paymentService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.productService = productService;
        this.orderItemService = orderItemService;
        this.paymentService = paymentService;
    }

    public OrderDTO createOrder(CreateOrderRequestDTO dto, UUID authenticatedUserId) {
        User user = userService.findById(authenticatedUserId);
        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDate.now());
        order.setOrderStatus(OrderStatus.IN_ELABORAZIONE);
        BigDecimal total = BigDecimal.ZERO;
        List<OrderItem> items = new ArrayList<>();
        for (CreateOrderItemDTO itemDto : dto.items()) {
            ProductAndService productAndService = productService.getById(itemDto.productId());
            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProductAndService(productAndService);
            item.setQuantity(itemDto.quantity());
            item.setItemPriceAtPurchase(productAndService.getPrice());
            total = total.add(productAndService.getPrice().multiply(BigDecimal.valueOf(itemDto.quantity())));
            items.add(item);
        }
        order.setTotalAmount(total);
        order.setItems(items);
        Order saved = orderRepository.save(order);
        List<OrderItemDTO> itemDTOs = saved.getItems().stream()
                .map(OrderItemDTO::from)
                .toList();
        return new OrderDTO(saved.getOrderId(), saved.getOrderDate(), saved.getTotalAmount(), saved.getOrderStatus(), user.getUserId(), itemDTOs);
    }

    public OrderDTO payOrder(UUID orderId, String paymentMethodNonce) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Ordine non trovato"));
        if (order.getOrderStatus() != OrderStatus.IN_ELABORAZIONE) {
            throw new IllegalArgumentException("questo ordine non è in attesa di pagamento");
        }
        String transactionId = paymentService.processPayment(paymentMethodNonce, order.getTotalAmount());
        order.setOrderStatus(OrderStatus.PAGATO);
        Order saved = orderRepository.save(order);
        return new OrderDTO(
                saved.getOrderId(),
                saved.getOrderDate(),
                saved.getTotalAmount(),
                saved.getOrderStatus(),
                saved.getUser().getUserId(),
                saved.getItems().stream().map(OrderItemDTO::from).toList()
        );
    }

    public List<OrderDTO> getUserOrders(UUID userId) {
        User user = userService.findById(userId);
        return orderRepository.findByUser(user).stream()
                .map(order -> new OrderDTO(
                        order.getOrderId(),
                        order.getOrderDate(),
                        order.getTotalAmount(),
                        order.getOrderStatus(),
                        user.getUserId(),
                        order.getItems().stream().map(OrderItemDTO::from).toList()
                ))
                .toList();
    }

    public OrderDTO updateOrderStatus(UUID orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Ordine non trovato con id: " + orderId));
        order.setOrderStatus(status);
        Order saved = orderRepository.save(order);
        return new OrderDTO(
                saved.getOrderId(),
                saved.getOrderDate(),
                saved.getTotalAmount(),
                saved.getOrderStatus(),
                saved.getUser().getUserId(),
                saved.getItems().stream().map(OrderItemDTO::from).toList()
        );
    }
}
