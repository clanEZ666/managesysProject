package Models.Models2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Product {
    private static final Logger log = LoggerFactory.getLogger(Product.class);
    private final int id;
    private final String title;
    private final double price;
    private final ProductCategory productCategory;

    public Product(int id, String title, double price, ProductCategory productCategory) {
        log.trace("создан Product");
        this.id = id;
        this.title = title;
        this.price = price;
        this.productCategory = productCategory;
    }

    public String getTitle() {
        log.trace("Начало метода Product.getTitle()");
        return title;
    }

    public ProductCategory getProductCategory() {
        log.trace("Начало метода Product.getProductCategory()");
        return productCategory;
    }

    public double getPrice() {
        log.trace("Начало метода Product.getPrice()");
        return price;
    }

    public int getId() {
        log.trace("Начало метода Product.getId()");
        return id;
    }

    @Override
    public String toString() {
        log.trace("Начало метода Product.toString()");
        return "Product{id = " + getId() + ", title = " + getTitle() + ", price = " + getPrice() +
                ", productCategory = " + getProductCategory() + '}';
    }

    public enum ProductCategory {
        FOOD,
        ELECTRONICS,
        CLOTHING
    }
}
