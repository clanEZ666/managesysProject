package repositories;

import Models.Models2.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository extends AbstractRepository{
    private List<Order> data = new ArrayList<>();


    @Override
    void add(Object o) {
        Order or = (Order) o;
        data.add(or);

    }

    @Override
    Object load(String path) {
        return null;
    }

    @Override
    boolean save(String path) {
        return false;
    }

}
