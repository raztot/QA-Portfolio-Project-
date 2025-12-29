package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProductsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By addBackpackBtn = By.id("add-to-cart-sauce-labs-backpack");
    private By cartIcon = By.className("shopping_cart_link");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void addBackpackToCart() { // Verifică să aibă acest nume exact
        wait.until(ExpectedConditions.elementToBeClickable(addBackpackBtn)).click();
    }

    public void goToCart() { // Verifică să aibă acest nume exact
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
    }
}