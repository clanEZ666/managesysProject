package org.example;

import controllers.CustomerController;
import repositories.CustomerRepository;
import services.CustomerService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CustomerRepository customerRepository = new CustomerRepository();
        CustomerService customerService = new CustomerService(customerRepository);
        CustomerController customerController = new CustomerController(customerService);

        customerController.start();
        customerController.closeScanner();
    }
}

