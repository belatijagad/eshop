package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
// import org.openqa.selenium.support.ui.ExpectedConditions;
// import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import java.time.Duration;
import java.util.List;
// import java.util.concurrent.TimeUnit;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class CreateProductFunctionalTest {
    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d/product", testBaseUrl, serverPort);
    }

    @Test
    void testEditProductButtonExists(ChromeDriver driver) throws Exception {
        driver.get(baseUrl + "/list");

        WebElement buttons = driver.findElement(By.cssSelector(".btn.btn-primary.btn-sm.mb-3"));

        assertEquals("Create product", buttons.getText());
    }

    @Test
    void testCreateProduct(ChromeDriver driver) throws Exception {
        driver.get(baseUrl + "/create");

        driver.findElement(By.id("nameInput")).sendKeys("Test Product");
        driver.findElement(By.id("quantityInput")).sendKeys("69");

        driver.findElement(By.id("submitButton")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        List<WebElement> products = driver.findElements(By.cssSelector(".product-name"));
        boolean productFound = products.stream()
            .anyMatch(product -> product.getText().contains("Test Product"));

        assertTrue(productFound, "Product not found on list page");
    }
}
