package controllers;

import Models.Models2.Product;
import org.slf4j.LoggerFactory;
import services.ProductService;

import java.util.Scanner;
import org.slf4j.Logger;
public class ProductController implements LowController{
    private final static Logger log = LoggerFactory.getLogger(ProductController.class);
    private final Scanner scanner;

    private final ProductService service;

    public ProductController(String path) {
        log.trace("Создание ProductController");
        this.service = new ProductService(path);
        this.scanner = new Scanner(System.in);
    }
    @Override
    public void start() {
        log.trace("Начало метода ProductController.start()");
        boolean goOn = true;
        String s;
        while (goOn) {
            Messages.START_MESSAGE.soutMassage();
            s = scanner.nextLine().trim();
            log.debug("Получено сообщение {}", s);
            switch (s.charAt(0)) {
                case '1':
                    log.info("сообщение воспринято как 1");
                    add();
                    break;
                case '2':
                    log.info("сообщение воспринято как 2");
                    show();
                    break;
                case '0':
                    log.info("сообщение воспринято не как 1 или 2");
                    goOn = false;
                    break;
                default:
                    Messages.ERR_GET_START.soutMassage();
            }
        }
    }

    public void add() {
        log.trace("Начало метода ProductController.add()");
        System.out.println();
        String s;
        String title;
        double price;
        Product.ProductCategory category;

        do {
            Messages.GET_TITLE.soutMassage();
            s = scanner.nextLine();
            log.debug("Получено сообщение {}", s);
            title = s;
            break;
        } while (true);

        do {
            Messages.GET_PRICE.soutMassage();
            try {
                s = scanner.nextLine();
                log.debug("Получено сообщение {}", s);
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
                log.debug("Получено сообщение {}", s);
                category = Product.ProductCategory.valueOf(s.toUpperCase());
                break;
            } catch (IllegalArgumentException e) {
                Messages.ERR_GET_CATEGORY.soutMassage();
            }
        } while (true);

        service.add(title, price, category);
    }

    public void show() {
        log.trace("Начало метода ProductController.show()");
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
