package controllers;

import Models.Models2.Customer;
import repositories.CustomerRepository;
import services.CustomerService;
import java.util.Scanner;

public class CustomerController implements LowController{
    private final CustomerService customerService;
    private final Scanner scanner;
    private final static String DEFOULT_PATH = "src/main/java/dataPath/customers.csv";

    /**
     * Конструкторы класса `CustomerController`.
     *
     *
     */


    public CustomerController() {
        this.customerService = new CustomerService(new CustomerRepository());
        this.scanner = new Scanner(System.in);
    }



    /**
     * Запускает основной цикл программы.
     * Выводит меню и обрабатывает выбор пользователя.
     * Бесконечно запрашивает действие пользователя пока не будет выбрано "Назад"
     */
    @Override
    public void start() {

        while (true) {
            Message.START_MENU.printMessage();

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addCustomer();
                    saveCustomers();
                    break;
                case 2:
                    loadCustomers();
                    showAllCustomers();
                    break;

                case 0:
                    Message.EXIT_CUSTOMER_MANAGEMENT.printMessage();
                    return;
                default:
                    Message.INVALID_CHOICE.printMessage();
            }
        }
    }


    /**
     * Метод для добавления нового покупателя.
     * Запрашивает имя и тип покупателя у пользователя,
     * после чего добавляет покупателя через сервис.
     * Обрабатывает невалидный ввод типа покупателя и выводит соответствующее сообщение
     */

    private void addCustomer() {
        Message.ENTER_CUSTOMER_NAME.printMessage();
        String name = scanner.nextLine();
        Message.ENTER_CUSTOMER_TYPE.printMessage();
        String typeInput = scanner.nextLine();
        try {
            Customer.CustomerType typeBuyer = Customer.CustomerType.valueOf(typeInput.toUpperCase());
            customerService.addCustomer(name, typeBuyer);
            Message.CUSTOMER_ADDED.printMessage();

        } catch (IllegalArgumentException e) {
            Message.INVALID_CHOICE.printMessage();
        }
    }

    /**
     * Метод для сохранения данных о покупателях в файл.
     * Запрашивает имя файла у пользователя,
     * затем вызывает метод сервиса для сохранения данных.
     */

    private void saveCustomers() {
        String filename = DEFOULT_PATH;
        customerService.saveCustomers(filename);
        Message.DATA_SAVED_TO_FILE.printMessage(filename);
    }

    /**
     * Метод для загрузки данных о покупателях из файла.
     * Запрашивает имя файла у пользователя,
     * затем вызывает метод сервиса для загрузки данных.
     */

    private void loadCustomers() {
        Message.ENTER_FILENAME_FOR_LOAD.printMessage();
        String filename = DEFOULT_PATH;
        customerService.loadCustomers(filename);
        Message.DATA_LOADED_FROM_FILE.printMessage(filename);
    }

    /**
     * Метод для отображения всех покупателей
     * Вызывает метод сервиса для отображения списка покупателей.
     * Выводит список покупателей в консоль
     */

    private void showAllCustomers() {
        customerService.showAllCustomers();
    }

    /**
     * Перечисление сообщений для вывода.
     */

    enum Message {
        START_MENU(
                """
                        ===== Управление покупателями =====
                        1. Добавить покупателя
                        2. Показать всех покупателей
                        0. Назад"""),

        EXIT_CUSTOMER_MANAGEMENT("Выход из управления покупателями."),
        INVALID_CHOICE("Некорректный выбор. Пожалуйста, попробуйте снова."),
        ENTER_CUSTOMER_NAME("Введите имя покупателя: "),
        ENTER_CUSTOMER_TYPE("Введите тип покупателя (NEW, REGULAR, VIP): "),
        CUSTOMER_ADDED("Покупатель добавлен."),
        INVALID_CUSTOMER_TYPE("Некорректный тип покупателя. Попробуйте снова."),
        ENTER_FILENAME_FOR_SAVE("Введите имя файла для сохранения: "),
        DATA_SAVED_TO_FILE("Данные сохранены в файл: "),
        DATA_LOADED_FROM_FILE("Данные загружены из файла: "),
        ENTER_FILENAME_FOR_LOAD("Введите имя файла для загрузки: ");


        private final String message;

        Message(String massage) {
            this.message = massage;

        }

        /**
         * Выводит сообщение в консоль.
         */

        private void printMessage() {
            System.out.println(message);
        }

        /**
         * Выводит сообщение в консоль с добавлением дополнительного аргумента.
         *
         * @param arg Дополнительный аргумент, который будет добавлен к сообщению.
         */
        public void printMessage(String arg) {
            System.out.println(message + arg);
        }


    }
}
