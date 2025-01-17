package repositories;

import Models.Models2.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class CustomerRepositoryTest {

    private CustomerRepository repository;
    private String testFileName;

    @BeforeEach
    void setUp() {
        repository = new CustomerRepository();
        testFileName = "testCustomers.csv";
    }


    @Test
    void testGetAllCustomers() {
        repository.add(new Customer("Test Customer 1", Customer.CustomerType.REGULAR));
        repository.add(new Customer("Test Customer 2", Customer.CustomerType.VIP));

        Map<Integer, Customer> allCustomers = repository.getAllCustomers();

        Assertions.assertEquals(2, allCustomers.size());
        Assertions.assertTrue(allCustomers.values().stream().anyMatch(c -> c.getName().equals("Test Customer 1")));
        Assertions.assertTrue(allCustomers.values().stream().anyMatch(c -> c.getName().equals("Test Customer 2")));
    }



    @Test
    void testLoad() throws IOException {
        String content = """
                1,Test Customer,REGULAR
                2,VIP Customer,VIP""";
        Path path = Path.of(testFileName);
        Files.write(path,content.getBytes());
        repository.load(testFileName);

        Map<Integer, Customer> allCustomers = repository.getAllCustomers();
        Assertions.assertEquals(2, allCustomers.size());
        Assertions.assertTrue(allCustomers.values().stream().anyMatch(c -> c.getName().equals("Test Customer")));
        Assertions.assertTrue(allCustomers.values().stream().anyMatch(c -> c.getName().equals("VIP Customer")));

        Files.delete(path);

    }

    @Test
    void testAdd() {
        Customer customer = new Customer("New Customer", Customer.CustomerType.REGULAR);
        repository.add(customer);
        Map<Integer, Customer> allCustomers = repository.getAllCustomers();

        Assertions.assertEquals(1, allCustomers.size());
        Assertions.assertTrue(allCustomers.values().stream().anyMatch(c -> c.getName().equals("New Customer")));
        Assertions.assertTrue(allCustomers.values().stream().anyMatch(c -> c.getType().equals(Customer.CustomerType.REGULAR)));

    }

    @Test
    void testSave() throws IOException {
        repository.add(new Customer("Test Customer", Customer.CustomerType.REGULAR));
        repository.save(testFileName);
        Assertions.assertTrue(new File(testFileName).exists());
        String content = Files.readString(Path.of(testFileName));
        Assertions.assertTrue(content.contains("Test Customer,REGULAR"));
        Files.delete(Path.of(testFileName));
    }

    @Test
    void testSerializeToFile() throws IOException {
        repository.add(new Customer("Test Customer 1", Customer.CustomerType.REGULAR));
        repository.add(new Customer("Test Customer 2", Customer.CustomerType.VIP));
        repository.serializeToFile(testFileName);
        Assertions.assertTrue(new File(testFileName).exists());

        String content = Files.readString(Path.of(testFileName));
        Assertions.assertTrue(content.contains("Test Customer 1,REGULAR"));
        Assertions.assertTrue(content.contains("Test Customer 2,VIP"));
        Files.delete(Path.of(testFileName));
    }

    @Test
    void testDeserializeFromFile() throws IOException {
        String content = """
                1,Test Customer,REGULAR
                2,VIP Customer,VIP""";
        Path path = Path.of(testFileName);
        Files.write(path,content.getBytes());

        repository.deserializeFromFile(testFileName);
        Map<Integer, Customer> allCustomers = repository.getAllCustomers();
        Assertions.assertEquals(2, allCustomers.size());
        Assertions.assertTrue(allCustomers.values().stream().anyMatch(c -> c.getName().equals("Test Customer")));
        Assertions.assertTrue(allCustomers.values().stream().anyMatch(c -> c.getName().equals("VIP Customer")));

        Files.delete(path);

    }

}
