package services;

import Models.Models2.Customer;
import exception.CustomerNotFoundException;

import java.util.ArrayList;
import java.util.List;


public class CustomerService {
    private List<Customer> customers;

    public CustomerService() {
        this.customers = new ArrayList<>();
    }

    public void addCustomer(String name, Customer.CustomerType typeBuyer) {
        Customer customer = new Customer(name, typeBuyer);
        customers.add(customer);
    }

    public void showAllCustomers() {
        if (customers.isEmpty()) {
            System.out.println("Нет покупателей.");
            return;
        }
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    public Customer findCustomerById(int id) {
        return customers.stream()
                .filter(customer -> customer.getId() == id)
                .findFirst()
                .orElseThrow(() -> new CustomerNotFoundException("Покупатель с ID " + id + " не найден."));
    }

}
