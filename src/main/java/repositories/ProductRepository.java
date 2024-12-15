package repositories;

import Models.Models2.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository extends AbstractRepository{
    private List<Product> data = new ArrayList<>();


    @Override
    void add(Object o) {
        Product p = (Product) o;
        data.add(p);
    }

    @Override
    Object load() {
        return null;
    }

    @Override
    boolean save() {
        return false;
    }

}
