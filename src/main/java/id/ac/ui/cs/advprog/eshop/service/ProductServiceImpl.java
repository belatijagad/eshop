package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        productRepository.create(product);
        return product;
    }

    @Override
    public List<Product> findAll() {
        Iterator<Product> productIterator = productRepository.findAll();
        List<Product> allProduct = new ArrayList<>();
        productIterator.forEachRemaining(allProduct::add);
        return allProduct;
    }

    @Override 
    public Product getById(String productId) {
        return productRepository.getById(productId);
    }

    @Override
    public Product update(String id, Product updatedProduct) {
        Product existingProduct = productRepository.getById(id);
        if (existingProduct != null) {
            existingProduct.setProductName(updatedProduct.getProductName());
            existingProduct.setProductQuantity(updatedProduct.getProductQuantity());
        }
        return existingProduct;
    }

    @Override
    public boolean delete(String id, Product product) {
        return productRepository.deleteById(id);
    }
}
