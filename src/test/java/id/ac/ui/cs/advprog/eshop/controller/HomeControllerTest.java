package id.ac.ui.cs.advprog.eshop.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class HomeControllerTest {
    @Test
    void testHomePage() {
        HomeController homeController = new HomeController();
        String viewName = homeController.homePage();
        assertEquals("home", viewName);
    }
}
