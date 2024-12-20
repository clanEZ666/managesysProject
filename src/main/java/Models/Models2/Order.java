package Models.Models2;

public class Order {
    private int ID;
    private String orderName;
    private String products;
    private String orderStatus;
    private static int currentId = 0;



    public Order(int id, String orderName, String products, String orderStatus) {
        this.ID = ++currentId;
        this.orderName = orderName;
        this.products = products;
        this.orderStatus = orderStatus;
    }


    public String getOrderName() {
        return orderName;
    }

    public static int getCurrentId() {
        return currentId;
    }

    public int getID() {
        return ID;
    }

    public int getId() {

        return ID;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderStatus() {

        return orderStatus;
    }

    public String getProducts() {

        return products;
    }
}
