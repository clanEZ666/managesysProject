package repositories;

import Models.Models2.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ProductRepository extends AbstractRepository{
    private Map<Integer, Product> data = new HashMap<>();

    @Override
    public void add(Object o) {
        Product c = (Product) o;
        data.put(c.getID(), c);
    }

    @Override
    public Object load(String path) {
        return null;
    }

    @Override
    public boolean save(String path) {

        return false;
    }

    public void show(){
        System.out.println(data.toString());
    }

    public Product getById(Integer id){
        data.keySet().stream()
                .filter(ID -> Objects.equals(ID, id))
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Покупатель с ID " + id + " не найден."));
        return data.get(id);
    }

}
