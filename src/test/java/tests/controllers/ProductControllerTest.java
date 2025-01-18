package tests.controllers;

import controllers.ProductController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

public class ProductControllerTest {
    private ProductController productController;

    @BeforeEach
    void _init(){
        productController = new ProductController();
    }

    @Test
    void startTest(){
        System.setIn(new ByteArrayInputStream("1".getBytes()));
        productController.start();
    }
}
