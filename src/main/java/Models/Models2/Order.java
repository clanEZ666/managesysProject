package Models.Models2;

import java.util.List;

public class Order {
    private int ID;
    private String orderName;
    private List<Integer> products;
    private String orderStatus;
    private int customerID;



    public Order(int id, int customerId, List<Integer> products, String orderStatus) {
        this.ID = id;
        this.customerID = customerId;
        this.products = products;
        this.orderStatus = orderStatus;
        this.orderName = orderName;
    }

    public int getCustomerID() {
        return customerID;
    }


    public int getId() {
        return ID;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public List<Integer> getProducts() {
        return products;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "Order{id=" + ID + ", customerID=" + customerID + ", products=" + products +
                ", status=" + orderStatus + "}";


    }

}
