package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver driver;

    // 1. Locatori (Elemente)
    private By emailField = By.id("user-name"); // Ajustează locatorii!
    private By passwordField = By.id("password"); // Ajustează locatorii!
    private By loginButton = By.id("login-button"); // Ajustează locatorii!
    private By errorMessage = By.cssSelector(".error-message-container"); // Ajustează locatorii!

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // 2. Acțiuni (Metode)
    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }

    // 3. Verificări/Stări
    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

    public WebElement getEmailField() {
        return driver.findElement(emailField);
    }
}