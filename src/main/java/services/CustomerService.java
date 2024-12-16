package services;

import Models.Models2.Customer;
import repositories.CustomerRepository;

import javax.naming.Name;
import java.util.List;

public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }



}
