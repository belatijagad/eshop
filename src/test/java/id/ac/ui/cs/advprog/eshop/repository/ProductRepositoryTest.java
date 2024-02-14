package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.Iterator;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {
    @InjectMocks
    ProductRepository productRepository;
    ProductService service;

    @BeforeEach
    void setUp() {

    }

    @Test
    void testGetById() {
        String productId = UUID.randomUUID().toString();

        Product product = new Product();
        product.setProductId(productId);
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(69);
        productRepository.create(product);

        Product returnedProduct = productRepository.getById(productId);
        assertNotNull(returnedProduct);
    }

    @Test
    void testGetByIdNonexisting() {
        String productId1 = UUID.randomUUID().toString();
        String productId2 = UUID.randomUUID().toString();

        Product product = new Product();
        product.setProductId(productId1);
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(69);
        productRepository.create(product);

        Product returnedProduct = productRepository.getById(productId2);
        assertNull(returnedProduct);
    }

    @Test
    void testCreateAndFind() {
        String productId = UUID.randomUUID().toString();

        Product product = new Product();
        product.setProductId(productId);
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(69);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        String productId1 = UUID.randomUUID().toString();
        Product product1 = new Product();
        product1.setProductId(productId1);
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(69);
        productRepository.create(product1);

        String productId2 = UUID.randomUUID().toString();
        Product product2 = new Product();
        product2.setProductId(productId2);
        product2.setProductName("Sampo Cap Yu");
        product2.setProductQuantity(69);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
    }

    @Test 
    void testCreateUpdateThenFind() {
        String productId = UUID.randomUUID().toString();

        Product product = new Product();
        product.setProductId(productId);
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(69);
        productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductId(productId);
        updatedProduct.setProductName("Sampo Cap Budi");
        updatedProduct.setProductQuantity(420);
        productRepository.update(updatedProduct);

        assertEquals(product.getProductName(), updatedProduct.getProductName());
        assertEquals(product.getProductQuantity(), updatedProduct.getProductQuantity());
    }

    @Test
    void testUpdateQuantityNotNegative() {
        String productId = UUID.randomUUID().toString();

        Product product = new Product();
        product.setProductId(productId);
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(69);
        productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductId(productId);
        updatedProduct.setProductName("Sampo Cap Budi");
        updatedProduct.setProductQuantity(-3);
        productRepository.update(updatedProduct);

        assertEquals(0, product.getProductQuantity());
    }

    @Test
    void testUpdateNonexistingProduct() {
        String productId1 = UUID.randomUUID().toString();
        String productId2 = UUID.randomUUID().toString();

        Product product = new Product();
        product.setProductId(productId1);
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(69);
        productRepository.create(product);

        Product updatedNonexisting = new Product();
        updatedNonexisting.setProductId(productId2);
        updatedNonexisting.setProductName("Sampo Cap Budi");
        updatedNonexisting.setProductQuantity(420);
        Product returnedProduct = productRepository.update(updatedNonexisting);

        assertNull(returnedProduct);
    }

    @Test 
    void testCreateDeleteThenFind() {
        String productId = UUID.randomUUID().toString();

        Product product = new Product();
        product.setProductId(productId);
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(69);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        productRepository.deleteById(productId);
        productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testDeleteNonexisting() {
        String productId1 = UUID.randomUUID().toString();
        String productId2 = UUID.randomUUID().toString();

        Product product = new Product();
        product.setProductId(productId1);
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(69);
        productRepository.create(product);

        Product deletedProduct = productRepository.deleteById(productId2);
        assertNull(deletedProduct);
    }
}
