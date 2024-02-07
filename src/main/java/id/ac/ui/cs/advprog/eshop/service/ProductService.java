package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;


public interface ProductService {
    public Product create(Product product);
    public Product update(String id, Product product);
    public boolean delete(String id, Product product);
    public List<Product> findAll();
    public Product getById(String productId);
}
