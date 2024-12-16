package repositories;

import Models.Models2.Customer;

import java.util.HashMap;
import java.util.Map;

public class CustomerRepository extends AbstractRepository{
    private Map<Integer, Customer> data = new HashMap<>();

    @Override
    public void add(Object o) {
        Customer c = (Customer) o;
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
}
