package services;

import Models.Models2.Customer;
import exception.CustomerNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repositories.CustomerRepository;

import java.util.Map;

/**
 * Сервис для управления покупателями.
 * Предоставляет методы для добавления, сохранения, загрузки,
 * отображения и поиска покупателей.
 */
public class CustomerService {
    private final CustomerRepository customerRepository;
    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    /**
     * Конструктор класса `CustomerService`.
     *
     * @param customerRepository Репозиторий для работы с покупателями.
     */
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Добавляет нового покупателя.
     *
     * @param name      Имя покупателя.
     * @param typeBuyer Тип покупателя.
     */
    public void addCustomer(String name, Customer.CustomerType typeBuyer) {
        Customer customer = new Customer(name, typeBuyer);
        customerRepository.add(customer);
        logger.info("Покупатель добавлен: " + customer);
    }

    /**
     * Сохраняет данные о покупателях в файл.
     *
     * @param filename Имя файла для сохранения.
     */
    public void saveCustomers(String filename) {
        customerRepository.save(filename);
        logger.info("Покупатели сохраненные в файле: " + filename);
    }

    /**
     * Загружает данные о покупателях из файла.
     *
     * @param filename Имя файла для загрузки.
     */
    public void loadCustomers(String filename) {
        customerRepository.load(filename);
        logger.info("Покупатели загружены из файла: " + filename);
    }

    /**
     * Отображает всех покупателей.
     * Получает всех покупателей из репозитория и выводит их в консоль.
     * Если список покупателей пуст, выводит соответствующее сообщение.
     */
    public void showAllCustomers() {
        Map<Integer, Customer> customers = customerRepository.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("Покупатели не найдены.");
            logger.info("Покупатели не найдены.");
            return;
        }
        for (Customer customer : customers.values()) {
            System.out.println(customer);
        }
        logger.info("Отображаются все покупатели из файла.");
    }

    /**
     * Находит покупателя по его ID.
     *
     * @param id ID покупателя, которого нужно найти.
     * @return Покупатель с заданным ID.
     * @throws CustomerNotFoundException Если покупатель с заданным ID не найден.
     */
    public Customer findCustomerById(int id) {
        Map<Integer, Customer> customers = customerRepository.getAllCustomers();
        return customers.values().stream()
                .filter(customer -> customer.getId() == id)
                .findFirst()
                .orElseThrow(() -> {
                    logger.error("Покупатель с ID " + id + " не найден.");
                    return new CustomerNotFoundException("Покупатель с ID " + id + " не найден.");
                });
    }
}


