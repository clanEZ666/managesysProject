package tests.controllers;

import Models.Models2.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.OrderRepository;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class OrderRepositoryTest {

    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        orderRepository = new OrderRepository();
    }


    @Test
    void testAddAndLoad() {
        Order order = new Order(1, 123, List.of(1, 2), Order.OrderStatus.NEW);
        orderRepository.add(order);

        Map<Integer, Order> orders = orderRepository.load(null);
        assertNotNull(orders);
        assertEquals(1, orders.size());
        assertTrue(orders.containsKey(1));
    }


    @Test
    void testFindById() {
        Order order = new Order(1, 123, List.of(1, 2), Order.OrderStatus.NEW);
        orderRepository.add(order);

        Order fetchedOrder = orderRepository.findById(1);
        assertNotNull(fetchedOrder);
        assertEquals(1, fetchedOrder.getId());
    }

    @Test
    void testFindByIdThrowsException() {
        assertThrows(RuntimeException.class, () -> orderRepository.findById(999));
    }

    @Test
    void testGetNextId() {
        int nextId = orderRepository.getNextId();
        assertTrue(nextId > 0);
    }

    @Test
    void testSave() throws IOException {
        Order order = new Order(1, 123, List.of(1, 2), Order.OrderStatus.NEW);
        orderRepository.add(order);

        boolean result = orderRepository.save(null);
        assertTrue(result);
    }
}