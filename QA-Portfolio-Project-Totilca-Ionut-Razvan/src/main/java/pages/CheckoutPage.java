package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    private WebDriver driver;

    // 1. Locatori (Elemente)

    // Etapa 1: Informații client
    private By firstNameField = By.id("first-name");
    private By lastNameField = By.id("last-name");
    private By zipCodeField = By.id("postal-code");
    private By continueButton = By.id("continue");

    // Etapa 2: Sumar comandă
    private By finishButton = By.id("finish");

    // Etapa 3: Confirmare
    private By thankYouHeader = By.className("complete-header");

    // Constructor
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    // 2. Acțiuni (Metode)

    /** Etapa 1: Introducere informații și continuare */
    public void enterCheckoutInformation(String firstName, String lastName, String zipCode) {
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(zipCodeField).sendKeys(zipCode);
        driver.findElement(continueButton).click();
    }

    /** Etapa 2: Finalizare comandă */
    public void clickFinish() {
        driver.findElement(finishButton).click();
    }

    /** Etapa 3: Verificare mesaj de confirmare */
    public String getThankYouMessage() {
        return driver.findElement(thankYouHeader).getText();
    }
}