package repositories;

import Models.Models2.Order;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderRepository extends AbstractRepository {
    private final String filePath = "orders.txt";
    private final String idFilePath = "ordersId.txt";
    private Map<Integer, Order> data = new HashMap<>();

    @Override
    public void add(Object o) {
        Order order = (Order) o;
        data.put(order.getId(), order);
    }

    @Override
    public Map<Integer, Order> load(Object o) {
        if (data.isEmpty()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(";");
                    int id = Integer.parseInt(parts[0]);
                    int customerID = Integer.parseInt(parts[1]);
                    String statusString = parts[2];
                    Order.OrderStatus status = Order.OrderStatus.valueOf(statusString);
                    List<Integer> productId = Arrays.stream(parts[3].split(","))
                            .map(Integer::parseInt)
                            .toList();



                    Order order = new Order(id, customerID, productId, status);
                    data.put(id, order);
                }
            } catch (IOException e) {
                System.err.println("Ошибка загрузки данных: " + e.getMessage());
            }
        }
        return data;
    }

    @Override
    public boolean save(Object o) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Order order : data.values()) {
                writer.write(String.format("%d;%d;%s;%s%n",
                        order.getId(),
                        order.getCustomerID(),
                        order.getOrderStatus(),
                        String.join(",", order.getProducts().stream()
                                .map(String::valueOf)
                                .toList())));
            }
            return true;
        } catch (IOException e) {
            System.err.println("Ошибка сохранения данных: " + e.getMessage());
            return false;
        }
    }

    public Order findById(int id) {
        if (!data.containsKey(id)) {
            throw new RuntimeException("Заказ не найден");
        }
        return data.get(id);
    }

    public int getNextId() {
        int lastId = 0;
        File idFile = new File(idFilePath);


        if (!idFile.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(idFilePath))) {
                writer.write("0");
                System.out.println("Файл ID создан. Установлен начальный ID: 0");
            } catch (IOException e) {
                System.err.println("Ошибка создания файла ID: " + e.getMessage());
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(idFilePath))) {
            lastId = Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            lastId = 0;
        }


        int nextId = lastId + 1;
        System.out.println("Следующий ID: " + nextId);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(idFilePath))) {
            writer.write(String.valueOf(nextId));
            System.out.println("Новый ID сохранён в файл: " + nextId);
        } catch (IOException e) {
            System.err.println("Ошибка сохранения нового ID: " + e.getMessage());
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(idFilePath))) {
            lastId = Integer.parseInt(reader.readLine());
            System.out.println("Прочитан последний ID из файла: " + lastId);
        } catch (IOException | NumberFormatException e) {
            lastId = 0;
            System.out.println("Ошибка чтения файла ID. Используем начальное значение: " + lastId);
        }



        return nextId;


    }
}
