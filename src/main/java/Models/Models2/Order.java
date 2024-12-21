package Models.Models2;

public class Order {
    private int id;
    private String orderName;
    private String products;
    private String orderStatus;
    private String New;
    private String Processing;
    private String Completed;
    private String Cancelled;


    public Order(int id, String orderName, String products, String orderStatus) {
        this.id = id;
        this.orderName = orderName;
        this.products = products;
        this.orderStatus = orderStatus;
    }


    public String getOrderName() {
        return orderName;
    }

    public int getId() {
        return id;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public String getProducts() {
        return products;
    }
}
