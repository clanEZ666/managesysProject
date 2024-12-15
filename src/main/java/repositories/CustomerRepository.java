package repositories;

import Models.Models2.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepository extends AbstractRepository{
    private List<Customer> data = new ArrayList<>();

    void add(Customer c){
        data.add(c);
    }

    @Override
    void add(Object o) {
        Customer c = (Customer) o;
        data.add(c);
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
