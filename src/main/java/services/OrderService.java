package services;

import Models.Models2.Customer;
import Models.Models2.Order;
import Models.Models2.Product;
import repositories.OrderRepository;

import java.util.List;

public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


}
