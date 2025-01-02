package controllers;

import Models.Models2.Product;
import services.ProductService;

import java.util.Scanner;

public class ProductController {

    private final Scanner scanner;

    private final ProductService service;

    public ProductController(String path) {
        this.service = new ProductService(path);
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        Messages.START_MESSAGE.soutMassage();
        boolean goOn = true;
        String s;
        while (goOn) {
            s = scanner.nextLine().trim();
            switch (s.charAt(0)) {
                case '1':
                    add();
                    break;
                case '2':
                    show();
                    break;
                case '0':
                    goOn = false;
                    break;
                default:
                    Messages.ERR_GET_START.soutMassage();
            }
        }
    }

    public void add() {
        System.out.println();
        String s;
        String title;
        double price;
        Product.ProductCategory category;

        do {
            Messages.GET_TITLE.soutMassage();
            s = scanner.nextLine();
            title = s;
            break;
        } while (true);

        do {
            Messages.GET_PRICE.soutMassage();
            try {
                s = scanner.nextLine();
                price = Double.parseDouble(s.trim());
                break;
            } catch (NumberFormatException e) {
                Messages.ERR_GET_PRICE.soutMassage();
            }
        } while (true);

        do {
            Messages.GET_CATEGORY.soutMassage();
            try {
                s = scanner.nextLine();
                category = Product.ProductCategory.valueOf(s.toUpperCase());
                break;
            } catch (IllegalArgumentException e) {
                Messages.ERR_GET_CATEGORY.soutMassage();
            }
        } while (true);

        service.add(title, price, category);
    }

    public void show() {
        service.showAll();
    }

    /**
     * Я просто не знаю как это написать так, что-бы кровь из глаз не текла,
     * так-что пусть будет так
     */
    enum Messages {
        START_MESSAGE("""
                ===== Управление товарами =====
                1. Добавить товар
                2. Показать все товары
                0. Назад
                """),
        ERR_GET_START("Некорректный выбор. Пожалуйста, попробуйте снова."),
        GET_TITLE("Введите название товара: "),
        GET_PRICE("Введите цену товара: "),
        GET_CATEGORY("Выберите категорию (FOOD, ELECTRONICS, CLOTHING): "),
        ERR_GET_TITLE("Невозможное название товара"),
        ERR_GET_PRICE("Невозможная цена товара"),
        ERR_GET_CATEGORY("Невозможная категория товара");


        private final String message;

        Messages(String message) {
            this.message = message;
        }

        private void soutMassage() {
            System.out.println(message);
        }
    }

}
