package Models.Models2;

import java.util.List;

public class Order {
    public enum OrderStatus {
        NEW, PROCESSING, COMPLETED, CANCELLED
    }

    private int ID;
    private String orderName;
    private List<Integer> products;
    private OrderStatus orderStatus;
    private int customerID;

    public Order(int id, int customerId, List<Integer> products, OrderStatus orderStatus) {
        this.ID = id;
        this.customerID = customerId;
        this.products = products;
        this.orderStatus = orderStatus;  // Здесь принимается объект типа OrderStatus
        this.orderName = "Order " + id;
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getId() {
        return ID;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public List<Integer> getProducts() {
        return products;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "Order{id=" + ID + ", customerID=" + customerID + ", products=" + products +
                ", status=" + orderStatus + "}";
    }
}
