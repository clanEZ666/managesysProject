package tests.services;

import Models.Models2.Product;
import exception.ProductNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.ProductService;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductServiceTest {
    private ProductService productService;

    @BeforeEach
    void _init(){
        productService = new ProductService("src/main/java/dataPath/");
    }

    @Test
    void showAllTest(){
        productService.showAll();
    }

    @Test
    void getDyIdTest(){
        assertThrows(ProductNotFoundException.class, () -> productService.getDyId(0));
        productService.getDyId(3);
        productService.getDyId(2);
        productService.getDyId(1);
        assertThrows(ProductNotFoundException.class, () -> productService.getDyId(4));

    }

    @Test
    void addTest(){
        productService.add("Жмень", 0.17, Product.ProductCategory.FOOD);
        productService.showAll();

    }

}
