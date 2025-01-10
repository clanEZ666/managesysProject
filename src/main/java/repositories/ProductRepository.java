package repositories;

import Models.Models2.Product;
import controllers.ProductController;
import exception.CorraptedFileDataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductRepository {
    private final static Logger log = LoggerFactory.getLogger(ProductRepository.class);
    private final Path currentIdPath;
    private final Path productsPath;

    /**
     * При создании класса проверяет/создаёт задейстуемые файлы
     *
     * @param savePath - строка путь в файловой системе
     */
    public ProductRepository(String savePath) {
        log.trace("Создание ProductRepository");
        Path savePath1 = Paths.get(savePath);
        this.currentIdPath = savePath1.resolve("currentProductId.txt");
        this.productsPath = savePath1.resolve("products.txt");

        preloadCurrentId();
        preloadProductsFile();
    }

    /**
     * Загрузка последнего используемого ID
     */
    public void preloadCurrentId() {
        log.trace("Начало метода preloadCurrentId()");
        try {
            if (Files.exists(currentIdPath)) {
                String stringId = Files.readString(currentIdPath).trim();
                if (stringId.isEmpty()) {
                    saveCurrentId(1);
                }
            } else {
                Files.createDirectories(currentIdPath);
                Files.createFile(currentIdPath);
                saveCurrentId(1);
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Ошибка при обработке файла currentProductId.txt " + e.getMessage());
        }
    }

    public void preloadProductsFile() {
        log.trace("Начало метода preloadProductsFile()");
        try {
            if (!Files.exists(productsPath)) {
                Files.createDirectories(productsPath.getParent());
                Files.createFile(productsPath);
            }
        } catch (IOException e) {
            System.out.println("Ошибка при обработке файла products.txt " + e.getMessage());
        }
    }

    private static String objectToData(Object o) {
        log.trace("Начало метода objectToData()");
        if (o instanceof Product) {
            Product product = (Product) o;
            StringBuilder sb = new StringBuilder();
            sb.append(product.getId()).append(';').append(product.getTitle()).append(';').append(product.getPrice())
                    .append(';').append(product.getProductCategory()).append('\n');
            return sb.toString();
        } else {
            log.warn("Ожидаемый класс - Product, получен класс - {}", o.getClass());
            return "";
        }
    }

    private static Object dataToObject(String data) throws CorraptedFileDataException {
        log.trace("Начало метода dataToObject()");
        String[] fields = data.split(";");
        if (fields.length != 4) {
            throw new CorraptedFileDataException("Не возможно преобразовать строку: " + data + " в объект");
        }
        try {
            int id = Integer.parseInt(fields[0]);
            String title = fields[1];
            double prise = Double.parseDouble(fields[2]);
            Product.ProductCategory category = Product.ProductCategory.valueOf(fields[3]);
            return new Product(id, title, prise, category);
        } catch (IllegalArgumentException e) {
            throw new CorraptedFileDataException("Ошибка при преобразовании данных из строки: " + data + " " + e.getMessage());
        }
    }

    public Map<Integer, Object> loadMap() {
        log.trace("Начало метода loadMap()");
        try {
            List<String> mapData = Files.readAllLines(productsPath);
            return mapData.stream().collect(Collectors.toMap(
                    s -> Integer.parseInt(s.split(";")[0]),
                    s -> {
                        try {
                            return dataToObject(s);
                        } catch (CorraptedFileDataException e) {
                            throw new RuntimeException(e);
                        }
                    }
            ));
        } catch (IOException e) {
            log.warn("Ошибка при загрузке данных из products.txt");
            return new HashMap<>();
        }
    }

    public int loadCurrentId() throws CorraptedFileDataException {
        log.trace("Начало метода loadCurrentId()");
        try {
            List<String> curId = Files.readAllLines(currentIdPath);
            if (curId.size() != 1) {
                throw new CorraptedFileDataException("Ошибка при загрузке данных из currentProductId.txt");
            }
            try {
                int result = Integer.parseInt(String.valueOf(curId.getFirst()));
                saveCurrentId(result + 1);
                return result;
            } catch (NumberFormatException e) {
                throw new CorraptedFileDataException("Не удалось преобразовать " + curId);
            }
        } catch (IOException e) {
            throw new CorraptedFileDataException("Ошибка при загрузке данных из currentProductId.txt");
        }
    }

    public boolean save(Object o) {
        log.trace("Начало метода save()");
        boolean ok = false;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(productsPath.toFile(), true))) {
            writer.write(objectToData(o));
            ok = true;
        } catch (IOException e) {
            log.warn("Ошибка при сохранении в файл products.txt");
        }

        return ok;
    }

    private void saveCurrentId(int curId) {
        log.trace("Начало метода saveCurrentId()");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(currentIdPath.toFile(), true))) {
            writer.write(String.valueOf(curId));
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении в файл currentProductId.txt");
        }
    }


}
