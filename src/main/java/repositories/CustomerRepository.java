package repositories;

import Models.Models2.Customer;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CustomerRepository extends AbstractRepository {
    private final Map<Integer, Customer> data = new HashMap<>();
    private int currentId = 1; // Для генерации уникальных ID

    public Map<Integer, Customer> getAllCustomers() {
        return new HashMap<>(data);
    }

    @Override

    public Object load(Object o) {
        if (o instanceof String) {
            deserializeFromFile((String) o);
            return null;
        }
        return null;
    }

    @Override
    public void add(Object o) {
        if (o instanceof Customer) {
            Customer customer = (Customer) o;
            customer.setId(currentId++); // Устанавливаем уникальный ID
            data.put(customer.getId(), customer);
        }
    }

    @Override
    public boolean save(Object o) {
        if (o instanceof String) {
            serializeToFile((String) o);
            return true;
        }
        return false;
    }

    public void serializeToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Customer customer : data.values()) {
                writer.write(customer.getId() + "," + customer.getName() + "," + customer.getType());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deserializeFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                Customer.CustomerType typeBuyer = Customer.CustomerType.valueOf(parts[2].toUpperCase());

                Customer customer = new Customer(name, typeBuyer);
                customer.setId(id); // Устанавливаем ID
                data.put(id, customer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка при чтении типа покупателя: " + e.getMessage());
        }
    }
}