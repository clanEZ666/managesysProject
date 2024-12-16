package Models.Models2;

public class Product {
    private int ID;
    private String title;
    private double price;
    private String category;
    private static int currentId = 0;



    public Product(int ID, String title, double price, String category) {
        this.ID = ++currentId;
        this.title = title;
        this.price = price;
        this.category = category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public static int getCurrentId() {
        return currentId;
    }

    public double getPrice() {
        return price;
    }

    public int getID() {
        return ID;
    }
}
