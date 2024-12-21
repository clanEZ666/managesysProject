package services;

import Models.Models2.Customer;
import exception.CustomerNotFoundException;
import repositories.CustomerRepository;
import java.util.Map;

public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void addCustomer(String name, Customer.CustomerType typeBuyer) {
        Customer customer = new Customer(name, typeBuyer);
        customerRepository.add(customer);
    }


    public void showAllCustomers() {
        Map<Integer, Customer> customers = customerRepository.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("Нет покупателей.");
            return;
        }
        for (Customer customer : customers.values()) {
            System.out.println(customer);
        }
    }

    public Customer findCustomerById(int id) {
        Map<Integer ,Customer> customers = customerRepository.getAllCustomers();
        return customers.values().stream()
                .filter(customer -> customer.getId() == id)
                .findFirst()
                .orElseThrow(() -> new CustomerNotFoundException("Покупатель с ID " + id + " не найден."));


    }
}





