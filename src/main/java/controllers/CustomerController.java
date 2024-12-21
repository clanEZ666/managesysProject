package controllers;

import Models.Models2.Customer;
import exception.CustomerNotFoundException;
import services.CustomerService;

public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void addCustomer(String name, Customer.CustomerType typeBuyer) {
        customerService.addCustomer(name, typeBuyer);
    }

    public void showAllCustomers() {
        customerService.showAllCustomers();
    }

    public Customer findCustomerById(int id) {
        return customerService.findCustomerById(id);
    }
}


