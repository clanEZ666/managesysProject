package tests.repositories;
import Models.Models2.Product;
import exception.CorraptedFileDataException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import repositories.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductRepositoriesTest {

    private ProductRepository pr;

    @BeforeEach
    void _init() {
        pr = new ProductRepository("src/main/java/dataPath/");
    }

    @Test
    @DisplayName("Сохранение объектов")
    void testSave() {

        Product prd1 = new Product(1, "Яблоко", 10.53, Product.ProductCategory.FOOD);
        Product prd2 = new Product(2, "Не Яблоко", 1000, Product.ProductCategory.ELECTRONICS);
        Product prd3 = new Product(3, "Очень Не Яблоко", 1, Product.ProductCategory.CLOTHING);
        String prd4 = "";

        boolean resalt = pr.save(prd1);
        assertTrue(resalt);
        resalt = pr.save(prd2);
        assertTrue(resalt);
        resalt = pr.save(prd3);
        assertTrue(resalt);
        resalt = pr.save(prd4);
        assertFalse(resalt);
        resalt = pr.save(null);
        assertFalse(resalt);
    }

    @Test
    @DisplayName("Сохранение curId")
    void testSaveCurId() throws CorraptedFileDataException {
        pr.loadCurrentId();
        pr.loadCurrentId();
        pr.loadCurrentId();
        pr.loadCurrentId();
        pr.loadCurrentId();
    }


}
