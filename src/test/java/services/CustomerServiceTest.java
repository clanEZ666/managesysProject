package services;

import Models.Models2.Customer;
import exception.CustomerNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.CustomerRepository;
import java.util.Map;


public class CustomerServiceTest {

    private CustomerService customerService;
    private TestCustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        customerRepository = new TestCustomerRepository();
        customerService = new CustomerService(customerRepository);
    }

    @Test
    void testAddCustomer() {
        String name = "Test Customer";
        Customer.CustomerType type = Customer.CustomerType.REGULAR;
        customerService.addCustomer(name, type);

        Map<Integer, Customer> customers = customerRepository.getAllCustomers();
        Assertions.assertEquals(1, customers.size());
        Assertions.assertTrue(customers.values().stream().anyMatch(c->c.getName().equals(name)));
        Assertions.assertTrue(customers.values().stream().anyMatch(c->c.getType().equals(type)));
    }

    @Test
    void testSaveCustomers() {
        String filename = "test.csv";
        customerService.saveCustomers(filename);
        Assertions.assertTrue(customerRepository.isSaved());
        Assertions.assertEquals(filename, customerRepository.getSavedFilename());
    }

    @Test
    void testLoadCustomers() {
        String filename = "test.csv";
        customerService.loadCustomers(filename);
        Assertions.assertTrue(customerRepository.isLoaded());
        Assertions.assertEquals(filename, customerRepository.getLoadedFilename());
    }

    @Test
    void testShowAllCustomersWithCustomers() {
        customerService.addCustomer("Test Customer 1", Customer.CustomerType.REGULAR);
        customerService.addCustomer("Test Customer 2", Customer.CustomerType.VIP);
        customerService.showAllCustomers();
    }

    @Test
    void testShowAllCustomersWithNoCustomers() {
        customerService.showAllCustomers();
    }

    @Test
    void testFindCustomerByIdWithExistingCustomer() {
        int id = 1;
        Customer customer = new Customer("Test Customer", Customer.CustomerType.REGULAR);
        customerRepository.add(customer);
        customer.setId(id);


        Customer foundCustomer = customerService.findCustomerById(id);
        Assertions.assertEquals(customer, foundCustomer);
    }

    @Test
    void testFindCustomerByIdWithNoExistingCustomer() {
        int id = 1;
        Assertions.assertThrows(CustomerNotFoundException.class, ()->customerService.findCustomerById(id));
    }

    private static class TestCustomerRepository extends CustomerRepository {
        private boolean saved = false;
        private boolean loaded = false;
        private String savedFilename;
        private String loadedFilename;


        @Override
        public boolean save(Object o) {
            if (o instanceof String) {
                savedFilename = (String) o;
                saved = true;
                return true;
            }
            return false;
        }

        public String getSavedFilename() {
            return savedFilename;
        }

        @Override
        public Object load(Object o) {
            if (o instanceof String) {
                loadedFilename = (String)o;
                loaded = true;
                return null;
            }
            return null;
        }

        public String getLoadedFilename(){
            return loadedFilename;
        }
        public boolean isSaved(){
            return saved;
        }
        public boolean isLoaded() { return loaded; }
    }
}