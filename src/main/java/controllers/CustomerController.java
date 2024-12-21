package controllers;

import Models.Models2.Customer;
import exception.CustomerNotFoundException;
import services.CustomerService;

import java.util.Scanner;

public class CustomerController {
    private final CustomerService customerService;
    private final Scanner scanner;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("===== Управление покупателями =====");
            System.out.println("1. Добавить покупателя");
            System.out.println("2. Показать всех покупателей");
            System.out.println("3. Сохранить покупателей");
            System.out.println("4. Загрузить покупателей");
            System.out.println("0. Назад");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Чистим буфер после ввода числа

            switch (choice) {
                case 1:
                    addCustomer();
                    break;
                case 2:
                    showAllCustomers();
                    break;
                case 3:
                    saveCustomers();
                    break;
                case 4:
                    loadCustomers();
                    break;
                case 0:
                    System.out.println("Выход из управления покупателями.");
                    return; // Выход из метода, завершение работы
                default:
                    System.out.println("Некорректный выбор. Пожалуйста, попробуйте снова.");
            }
        }
    }

    public void closeScanner() {
        scanner.close();
    }

    private void addCustomer() {
        System.out.print("Введите имя покупателя: ");
        String name = scanner.nextLine();
        System.out.print("Введите тип покупателя (NEW, REGULAR, VIP): ");
        String typeInput = scanner.nextLine();
        try {
            Customer.CustomerType typeBuyer = Customer.CustomerType.valueOf(typeInput.toUpperCase());
            customerService.addCustomer(name, typeBuyer);
            System.out.println("Покупатель добавлен.");
        } catch (IllegalArgumentException e) {
            System.out.println("Некорректный тип покупателя. Попробуйте снова.");
        }
    }

    private void saveCustomers() {
        System.out.print("Введите имя файла для сохранения: ");
        String filename = scanner.nextLine();
        customerService.saveCustomers(filename);
        System.out.println("Данные сохранены в файл: " + filename);
    }

    private void loadCustomers() {
        System.out.print("Введите имя файла для загрузки: ");
        String filename = scanner.nextLine();
        customerService.loadCustomers(filename);
        System.out.println("Данные загружены из файла: " + filename);
    }

    private void showAllCustomers() {
        customerService.showAllCustomers();
    }
}