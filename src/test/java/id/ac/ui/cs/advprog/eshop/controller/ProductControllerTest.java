package id.ac.ui.cs.advprog.eshop.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {
    
    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @Mock
    private Model model;
    private Product product;

    @BeforeEach
    void setUp() {
        String productId = UUID.randomUUID().toString();
        product = new Product();
        product.setProductId(productId);
        product.setProductName("Plushie Suisei");
        product.setProductQuantity(20);
    }

    @Test
    void testProductListPage() {
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        when(productService.findAll()).thenReturn(productList);

        String viewName = productController.productListingPage(model);

        assertEquals("productList", viewName);
        verify(model).addAttribute("products", productList);
    }

    @Test
    void testCreateProductGet() {
        String viewName = productController.createProductPage(model);

        assertEquals(viewName, "productCreate");
        verify(model).addAttribute(eq("product"), any(Product.class));
    }

    @Test
    void testCreateProductPost() {
        String redirectUrl = productController.createProductPost(product, model);

        assertEquals("redirect:/product/list", redirectUrl);
        verify(productService).create(product);
    }
}
