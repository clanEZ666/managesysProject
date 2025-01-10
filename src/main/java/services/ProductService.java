package services;

import Models.Models2.Product;
import exception.CorraptedFileDataException;
import exception.ProductNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repositories.ProductRepository;

public class ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository repository;

    public ProductService(String path) {
        log.trace("Создание ProductService");
        this.repository = new ProductRepository(path);
    }

    public void add(String title, double cost, Product.ProductCategory category) {
        log.trace("Начало метода ProductService.add()");
        try {
            repository.save(new Product(repository.loadCurrentId(), title, cost, category));
        } catch (CorraptedFileDataException e) {
            System.out.println("Невозможно получить доступ к текущему id: " + e.getMessage());
            System.out.println("Текущий id установлен на 1");
            repository.save(new Product(1, title, cost, category));
            repository.preloadCurrentId();
        }
    }

    public void showAll() {
        log.trace("Начало метода ProductService.showAll()");
        for (Object product : repository.loadMap().values()) {
            System.out.println(product);
        }
    }

    public void getDyId(int id) {
        log.trace("Начало метода ProductService.getDyId()");
        if (repository.loadMap().entrySet().stream().filter(s -> s.getKey().equals(id)).toList().size() != 1) {
            throw new ProductNotFoundException("Продукт с данным id не найден");
        } else {
            System.out.println(repository.loadMap().entrySet().stream().filter(s -> s.getKey().equals(id)).toList().getFirst().getValue());
        }
    }
}
