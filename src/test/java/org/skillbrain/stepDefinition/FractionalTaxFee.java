package org.skillbrain.stepDefinition;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.skillbrain.utilities.TestContextSetup;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class FractionalTaxFee {

    private double ticketPrice;
    private double calculatedTax;
    private WebDriver driver;
    private TestContextSetup testContextSetup;

    // Constructor cu dependency injection (Cucumber îl cheamă automat)
    public FractionalTaxFee(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
        this.driver = testContextSetup.getDriver();
    }

    @When("I click on attractions sidebar")
    public void iClickOnAttractionsSidebar() {
        System.out.println("Clicked attractions sidebar.");
        // Dacă trebuie click efectiv pe element:
        // WebElement sidebar = driver.findElement(By.xpath("//selector_sidebar"));
        // sidebar.click();
    }

    @When("I click on my attractions")
    public void iClickOnMyAttractions() {
        System.out.println("Clicked my attractions.");
        // Dacă trebuie click efectiv pe element:
        // WebElement myAttractions = driver.findElement(By.xpath("//selector_myAttractions"));
        // myAttractions.click();
    }

    @When("I click on GoToHub button")
    public void iClickOnGoToHubButton() {
        System.out.println("Clicked GoToHub button.");

        // Click pe GoToHub
        WebElement goToHubButton = driver.findElement(By.xpath("//button[text()='GoToHub']"));
        goToHubButton.click();

        // Wait până când cardul „Da” este vizibil
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[.//p[normalize-space()='Da']]")
        ));
    }

    @When("I click on the Buy Now button")
    public void iClickOnTheBuyNowButton() {
        // Găsim cardul cu titlul "Da"
        WebElement card = driver.findElement(By.xpath("//div[.//p[normalize-space()='Da']]"));

        // Citim prețul
        WebElement priceElement = card.findElement(By.xpath(".//p[contains(text(),'From')]"));
        String priceText = priceElement.getText();

        // Extragem valoarea numerică
        String numericPart = priceText.replaceAll("[^0-9.,]", "").replace(",", ".");
        ticketPrice = Double.parseDouble(numericPart);

        // Calculăm taxa 3%
        calculatedTax = Math.floor(ticketPrice * 0.03 * 100.0) / 100.0;

        System.out.println("Ticket price read: " + ticketPrice);
        System.out.println("Calculated tax: " + calculatedTax);

        // Wait și click pe Buy Now folosind selectorul CSS exact
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement buyNowButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("a[data-slot='button'].bg-primary.text-primary-foreground")
        ));

        buyNowButton.click();
        System.out.println("'Buy now' button clicked successfully.");
    }

    @Then("Check if tax is correct")
    public void checkIfTaxIsCorrect() {
        // Citim taxa afișată pe site (presupunem că există un element cu clasa 'tax-value')
        WebElement taxElement = driver.findElement(By.cssSelector(".tax-value"));
        String taxText = taxElement.getText().replaceAll("[^0-9.,]", "").replace(",", ".");
        double displayedTax = Double.parseDouble(taxText);

        System.out.println("Displayed tax: " + displayedTax);
        System.out.println("Calculated tax: " + calculatedTax);

        assertEquals(displayedTax, calculatedTax, "Tax calculation is incorrect!");
    }
}
