package repositories;

import Models.Models2.Order;

import java.util.HashMap;
import java.util.Map;

public class OrderRepository extends AbstractRepository{
    @Override
    public void add(Object o) {

    }

    @Override
    public Object load(Object o) {
        return null;
    }

    @Override
    public boolean save(Object o) {
        return false;
    }
}
//    private Map<Integer, Order> data = new HashMap<>();
//
//    @Override
//    public void add(Object o) {
//        Order c = (Order) o;
////        data.put(c.getID(), c);
//    }
//
//    @Override
//    public Object load(String path) {
//        return null;
//    }
//
//    @Override
//    public boolean save(String path) {
//        return false;
//    }
//
//}
