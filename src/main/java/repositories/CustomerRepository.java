package repositories;

import Models.Models2.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepository extends AbstractRepository{
    private List<Customer> data = new ArrayList<>();

    @Override
    void add(Object o) {
        Customer c = (Customer) o;
        data.add(c);
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
