package repositories;

import Models.Models2.Customer;
import java.util.HashMap;
import java.util.Map;

public class CustomerRepository extends AbstractRepository {
    private final Map<Integer, Customer> data = new HashMap<>();

    // Получение всех покупателей
    public Map<Integer, Customer> getAllCustomers() {
        return new HashMap<>(data); // Возвращаем копию для защиты от изменений
    }

    @Override
    public Object load(Object o) {
        //  для загрузки из файла
        return null;
    }

    // проверяет, является ли объект Customer, и вызывает соответствующий метод
    @Override
    public void add(Object o) {
        if (o instanceof Customer) {
            add(o);
        }
    }


    @Override
    public boolean save(Object o) {
        // для сохранение  в файл
        return false;
    }
}