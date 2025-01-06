package services;

import Models.Models2.Product;
import exception.ProductNotFoundException;
import repositories.ProductRepository;

public class ProductService {

    private final ProductRepository repository = new ProductRepository();

    public void addProduct(String title, double price, String category){
////        Product prd = new Product(title, price, category);
//        repository.add(prd);
    }

    public void showAllProducts(){
        repository.show();
    }

    public void getByID(int id) throws ProductNotFoundException {
        try{
//            System.out.println(repository.getByID());
        } catch (ProductNotFoundException e){
            System.out.println(e);
        }
    }
}
