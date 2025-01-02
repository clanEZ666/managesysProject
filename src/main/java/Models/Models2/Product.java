package Models.Models2;

public class Product {
    private final int id;
    private final String title;
    private final double price;
    private final ProductCategory productCategory;

    public Product(int id, String title, double price, ProductCategory productCategory) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.productCategory = productCategory;
    }

    public String getTitle() {
        return title;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public double getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Product{id = " + getId() + ", title = " + getTitle() + ", price = " + getPrice() +
                ", productCategory = " + getProductCategory() + '}';
    }

    public enum ProductCategory {
        FOOD,
        ELECTRONICS,
        CLOTHING
    }
}
