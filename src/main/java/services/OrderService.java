package services;
import Models.Models2.Customer;
import Models.Models2.Order;
import repositories.OrderRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderService {
    private final OrderRepository orderRepository;


    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void createOrder(int id, int customerId, List<Integer> productIds, String status) {
        Order order = new Order(id, customerId, productIds, status);
        orderRepository.add(order);
    }

    public List<Order> getAllOrders() {
        Map<Integer, Order> orders = orderRepository.load(null);
        if (orders == null) {
            return new ArrayList<>();
        }
        return List.copyOf(orders.values());
    }

    public void updateOrderStatus(int id, String status) {
        Order order = orderRepository.findById(id);
        order.setOrderStatus(status);
        orderRepository.save(order);
    }

    public int getNextOrderId() {
        return orderRepository.getNextId();
    }


}

