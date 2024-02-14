package id.ac.ui.cs.advprog.eshop.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;

public class ProductServiceImplTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProduct() {
        String productId = UUID.randomUUID().toString();

        Product product = new Product();
        product.setProductName("Test Product");
        product.setProductQuantity(10);
        product.setProductId(productId);

        when(productRepository.create(product)).thenReturn(product);
        Product createdProduct = productService.create(product);

        assertEquals("Test Product", createdProduct.getProductName());
        assertEquals(10, createdProduct.getProductQuantity());
        assertEquals(productId, createdProduct.getProductId());
    }

    @Test
    void testCreateAndFindAll() {
        List<Product> productList = new ArrayList<>();
        Product product1 = new Product();
        Product product2 = new Product();
        productList.add(product1);
        productList.add(product2);

        when(productRepository.findAll()).thenReturn(productList.iterator());
        List<Product> foundProducts = productService.findAll();

        assertEquals(2, foundProducts.size());
    }

    @Test
    void testGetProductById() {
        String productId = UUID.randomUUID().toString();

        Product product = new Product();
        product.setProductId(productId);

        when(productRepository.getById(productId)).thenReturn(product);
        Product foundProduct = productService.getById(productId);

        assertNotNull(foundProduct);
        assertEquals(productId, foundProduct.getProductId());
    }

    @Test
    void testUpdateProduct() {
        String productId = UUID.randomUUID().toString();

        Product product = new Product();
        product.setProductId(productId);
        product.setProductName("Test Product");
        product.setProductQuantity(5);

        when(productRepository.update(product)).thenReturn(product);
        Product updatedProduct = productService.update(product);

        assertEquals("Test Product", updatedProduct.getProductName());
        assertEquals(5, updatedProduct.getProductQuantity());
    }

    @Test
    void testDeleteProduct() {
        String productId = UUID.randomUUID().toString();

        Product product = new Product();
        product.setProductId(productId);
        product.setProductName("Test Product");
        product.setProductQuantity(5);

        when(productRepository.deleteById(productId)).thenReturn(product);
        Product deletedProduct = productService.delete(productId);

        assertNotNull(deletedProduct);
        assertEquals(productId, deletedProduct.getProductId());
    }
}
