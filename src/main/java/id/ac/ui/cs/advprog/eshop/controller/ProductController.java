package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    private final ProductService service;
    private final String productListRedirect;

    public ProductController(ProductService service) {
        this.service = service;
        this.productListRedirect = "redirect:/product/list";
    }

    @GetMapping("/create")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "ProductCreate";
    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute Product product, Model model) {
        service.create(product);
        return productListRedirect;
    }

    @GetMapping("/edit/{id}")
    public String editProductGet(@PathVariable String id, Model model) {
        Product product = service.getById(id);
        model.addAttribute("product", product);
        return "ProductUpdate";
    }

    @PutMapping("/edit")
    public String editProductPut(@ModelAttribute Product product) {
        service.update(product);
        return productListRedirect;
    }

    @PostMapping("/delete/{id}")
    public String deleteProductPost(@PathVariable String id) {
        service.delete(id);
        return productListRedirect;
    }

    @GetMapping("/list")
    public String productListingPage(Model model) {
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "ProductList";
    }
}