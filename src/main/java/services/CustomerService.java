package services;

import Models.Models2.Customer;
import exception.CustomerNotFoundException;
import repositories.CustomerRepository;
import java.util.List;

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
        List<Customer> customers = customerRepository.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("Нет покупателей.");
            return;
        }
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    public Customer findCustomerById(int id) {
        List<Customer> customers = customerRepository.getAllCustomers();
        return customers.stream()
                .filter(customer -> customer.getId() == id)
                .findFirst()
                .orElseThrow(() -> new CustomerNotFoundException("Покупатель с ID " + id + " не найден."));
    }
}





