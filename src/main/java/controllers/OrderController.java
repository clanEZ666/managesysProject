package controllers;

import services.OrderService;

import java.util.List;
import java.util.Scanner;

public class OrderController {

    private final OrderService orderService;
    private final Scanner scanner = new Scanner(System.in);

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public void manageOrders() {
        while (true) {
            System.out.println("===== Управление заказами =====");
            System.out.println("1. Создать заказ");
            System.out.println("2. Показать все заказы");
            System.out.println("3. Изменить статус заказа");
            System.out.println("0. Назад");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> createOrder();
                case 2 -> showAllOrders();
                case 3 -> updateOrderStatus();
                case 0 -> { return; }
                default -> System.out.println("Неверный выбор.");
            }
        }
    }

    private void createOrder() {

        int id = orderService.getNextOrderId();

        int customerId = getIntInput("Введите ID покупателя: ");

        // Получаем список товаров
        System.out.print("Введите ID товаров через запятую: ");
        String productInput = scanner.nextLine();
        List<Integer> productIds = List.of(productInput.split(",")).stream()
                .map(Integer::parseInt)
                .toList();

        String status = getStringInput("Введите статус заказа (NEW, PROCESSING, COMPLETED, CANCELLED): ");

        orderService.createOrder(id, customerId, productIds, status);
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
        orderService.getAllOrders().forEach(order -> System.out.println(order));
    }

    private void updateOrderStatus() {
        System.out.print("Введите ID заказа: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Введите новый статус заказа (NEW, PROCESSING, COMPLETED, CANCELLED): ");
        String status = scanner.nextLine();

        orderService.updateOrderStatus(id, status);
        System.out.println("Статус заказа обновлён.");
    }
}
