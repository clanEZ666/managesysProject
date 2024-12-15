package repositories;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository extends AbstractRepository{
    private List<Order> data = new ArrayList<>();

    static void add(Order o){
        data.add(o);
    }
}
