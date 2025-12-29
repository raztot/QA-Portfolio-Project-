package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private WebDriver driver;
    private By itemName = By.className("inventory_item_name");
    private By checkoutBtn = By.id("checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getItemName() {
        return driver.findElement(itemName).getText();
    }

    public void clickCheckout() {
        driver.findElement(checkoutBtn).click();
    }
}