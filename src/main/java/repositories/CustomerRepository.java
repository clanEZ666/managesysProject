package repositories;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepository extends AbstractRepository{
    private List<Customer> data = new ArrayList<>();

    static void add(Customer c){
        data.add(c);
    }
}
