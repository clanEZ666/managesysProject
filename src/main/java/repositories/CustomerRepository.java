package repositories;

import Models.Models2.Customer;

import java.util.ArrayList;
import java.util.List;


public class CustomerRepository extends AbstractRepository{
    private final List<Customer> data = new ArrayList<>();

    public void add(Customer customer) {
        data.add(customer);
    }

    @Override
    void add(Object o) {

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
