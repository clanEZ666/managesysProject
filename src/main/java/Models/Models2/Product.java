package Models.Models2;

public class Product {
    private int ID;
    private String title;
    private double price;
    private String category;
    private String Food;
    private String Electronics;
    private String Clothing;


    public Product(String title, double price, String category) {
        this.title = title;
        this.price = price;
        this.category = category;
    }


    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getID() {
        return ID;
    }
}
