package controllers;

import Models.Models2.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.OrderService;
import java.util.List;
import java.util.Scanner;




public class OrderController implements LowController {


    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final OrderService orderService;
    private final Scanner scanner = new Scanner(System.in);

    public OrderController() {
        this.orderService = new OrderService();
    }


    public void start() {
        logger.info("Запуск приложения: Управление заказами");
        while (true) {
            System.out.println("===== Управление заказами =====");
            System.out.println("1. Создать заказ");
            System.out.println("2. Показать все заказы");
            System.out.println("3. Изменить статус заказа");
            System.out.println("0. Назад");

            int choice = scanner.nextInt();
            scanner.nextLine();

            logger.debug("Пользователь выбрал: {}", choice);

            switch (choice) {
                case 1 -> createOrder();
                case 2 -> showAllOrders();
                case 3 -> updateOrderStatus();
                case 0 -> {
                    logger.info("Завершение работы приложения");
                    return;
                }
                default -> {
                    logger.warn("Неверный выбор: {}", choice);
                    System.out.println("Неверный выбор.");
                }
            }
        }
    }

    private void createOrder() {
        logger.info("Начало создания заказа");
        int id = orderService.getNextOrderId();
        logger.debug("Сгенерирован ID заказа: {}", id);

        int customerId = getIntInput("Введите ID покупателя: ");
        logger.debug("Введён ID покупателя: {}", customerId);

        // Ввод товаров
        System.out.print("Введите ID товаров через запятую: ");
        String productInput = scanner.nextLine();
        List<Integer> productIds = List.of(productInput.split(",")).stream()
                .map(Integer::parseInt)
                .toList();
        logger.debug("Введены ID товаров: {}", productIds);

        // Ввод статуса и преобразование в тип OrderStatus
        Order.OrderStatus status = null;
        while (status == null) {
            String statusInput = getStringInput("Введите статус заказа (NEW, PROCESSING, COMPLETED, CANCELLED): ");
            try {
                status = Order.OrderStatus.valueOf(statusInput.toUpperCase());
                logger.debug("Введён статус заказа: {}", status);
            } catch (IllegalArgumentException e) {
                logger.warn("Некорректный статус заказа: {}", statusInput);
                System.out.println("Ошибка: некорректный статус. Пожалуйста, попробуйте снова.");
            }
        }

        logger.info("Создание заказа с ID: {}", id);
        orderService.createOrder(id, customerId, productIds, status);
        logger.info("Заказ с ID {} успешно создан", id);
        System.out.println("Заказ с ID " + id + " создан.");
    }

    private int getIntInput(String prompt) {
        int input = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print(prompt);
            try {
                input = scanner.nextInt();
                scanner.nextLine();
                validInput = true;
            } catch (Exception e) {
                logger.error("Ошибка ввода: введено не число", e);
                System.out.println("Ошибка: введите число, а не буквы или другие символы.");
                scanner.nextLine();
            }
        }
        return input;
    }

    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private void showAllOrders() {
        logger.info("Запрос на вывод всех заказов");
        orderService.getAllOrders().forEach(order -> {
            logger.debug("Заказ: {}", order);
            System.out.println(order);
        });
    }

    private void updateOrderStatus() {
        logger.info("Начало обновления статуса заказа");
        System.out.print("Введите ID заказа: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        logger.debug("Введён ID заказа: {}", id);

        System.out.print("Введите новый статус заказа (NEW, PROCESSING, COMPLETED, CANCELLED): ");
        String status = scanner.nextLine();
        logger.debug("Введён новый статус: {}", status);

        try {
            orderService.updateOrderStatus(id, status);
            logger.info("Статус заказа с ID {} обновлён на {}", id, status);
            System.out.println("Статус заказа обновлён.");
        } catch (RuntimeException e) {
            logger.error("Ошибка обновления статуса заказа: {}", e.getMessage());
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
