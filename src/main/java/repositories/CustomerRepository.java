package repositories;

import Models.Models2.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CustomerRepository extends AbstractRepository  {
    private static final Logger logger = LoggerFactory.getLogger(CustomerRepository.class);
    private final Map<Integer, Customer> data = new HashMap<>();
    private int currentId = 1;



    /**
     * Получить всех покупателей.
     *
     * @return Копия мапы с данными о покупателях.
     */
    public Map<Integer, Customer> getAllCustomers() {
        return new HashMap<>(data);
    }

    /**
     * Загрузить данные из файла.
     *
     * @param o Имя файла для загрузки.
     * @return o
     */
    @Override
    public Object load(Object o) {
        if (o instanceof String) {
            deserializeFromFile((String) o);
            return o;

        }
        return null;
    }

    /**
     * Добавить нового покупателя.
     *
     * @param o Объект типа Customer для добавления.
     */
    @Override
    public void add(Object o) {
        if (o instanceof Customer customer) {
            customer.setId(currentId++);
            data.put(customer.getId(), customer);
            logger.info("Покупатель добавлен: {}", customer);
        }
    }

    /**
     * Сохранить данные в файл.
     *
     * @param o Имя файла для сохранения.
     * @return true, если успешно сохранено, иначе false.
     */
    @Override
    public boolean save(Object o) {
        if (o instanceof String) {
            serializeToFile((String) o);
            return true;
        }
        return false;
    }

    /**
     * Записать данные в файл.
     *
     * @param filename Имя файла для записи.
     */
    public void serializeToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Customer customer : data.values()) {
                writer.write(customer.getId() + "," + customer.getName() + "," + customer.getType());
                writer.newLine();
                logger.info("Создание файла");
            }
        } catch (IOException e) {
            logger.error("Ошибка записи в файл: ", e);
            throw new RuntimeException("Ошибка записи в файл", e);
        }
    }

    /**
     * Прочитать данные из файла.
     *
     * @param filename Имя файла для чтения.
     */
    public void deserializeFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                Customer.CustomerType typeBuyer = Customer.CustomerType.valueOf(parts[2].toUpperCase());

                Customer customer = new Customer(name, typeBuyer);
                customer.setId(id);
                data.put(id, customer);
            }
        } catch (IOException e) {
            logger.error("Ошибка чтения из файла: ", e);
            throw new RuntimeException("Ошибка чтения из файла", e);
        } catch (IllegalArgumentException e) {
            logger.error("Ошибка типа клиента: ", e);
            throw new RuntimeException("Ошибка типа клиента", e);
        }
    }


}
