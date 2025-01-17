package controllers;

import Models.Models2.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repositories.CustomerRepository;
import services.CustomerService;
import services.ProductService;

import java.util.Scanner;

public class MainController {
    private final static Logger log = LoggerFactory.getLogger(MainController.class);
    static final String DATA_PATH = "src/main/java/dataPath";
    static final LowController customerController = new CustomerController(new CustomerService(new CustomerRepository())); // Вот это сами чинить будете
    static final LowController orderController = new OrderController();
    static final LowController productController = new ProductController(DATA_PATH);




    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
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
                    productController.start();
                    break;
                case '2':
                    log.info("сообщение воспринято как 2");
                    customerController.start();
                    break;
                case '3':
                    log.info("сообщение воспринято как 3");
                    orderController.start();
                    break;
                case '0':
                    log.info("сообщение воспринято не как 1, 2 или 3");
                    goOn = false;
                    break;
                default:
                    Messages.ERR_GET_START.soutMassage();
            }
        }
    }




    enum Messages {
        START_MESSAGE("""
                ===== Главное меню =====
                1. Управление товарами
                2. Управление покупателями
                3. Управление заказами
                0. Выйти
                """),
        ERR_GET_START("Некорректный выбор. Пожалуйста, попробуйте снова.");


        private final String message;

        Messages(String message) {
            this.message = message;
        }

        private void soutMassage() {
            System.out.println(message);
        }
    }
}
