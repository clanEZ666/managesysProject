package repositories;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository extends AbstractRepository{
    private List<Product> data = new ArrayList<>();

    static void add(Product p){
        data.add(p);
    }
}
