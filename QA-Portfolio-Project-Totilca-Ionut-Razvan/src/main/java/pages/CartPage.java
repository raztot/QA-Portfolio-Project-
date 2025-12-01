package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class CartPage {
    private WebDriver driver;

    // 1. Locatori (Elemente)
    // Titlul paginii de Coș (pentru validare)
    private By pageTitle = By.className("title");
    // Containerul pentru un element individual din coș
    private By cartItem = By.className("cart_item");
    // Numele produsului dintr-un item din coș
    private By cartItemName = By.className("inventory_item_name");
    // Butonul de ștergere/scoatere din coș
    private By removeButton = By.xpath("//button[text()='Remove']");
    // Butonul de Checkout
    private By checkoutButton = By.id("checkout");
    // Butonul de Continuare Cumpărături
    private By continueShoppingButton = By.id("continue-shopping");

    // Constructor
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // 2. Acțiuni (Metode)

    /** TC-201: Vizualizare produse în coș */
    public List<WebElement> getCartItems() {
        // Returnează lista tuturor elementelor produs din coș
        return driver.findElements(cartItem);
    }

    /** Ajutor: Returnează numele unui produs specific dintr-un element din coș */
    public String getItemName(WebElement item) {
        return item.findElement(cartItemName).getText();
    }

    /** TC-202: Ștergerea unui produs din coș (elimină primul element găsit) */
    public void removeFirstItem() {
        // Găsește primul buton 'Remove' și apasă-l
        driver.findElement(removeButton).click();
    }

    /** Navigare la Checkout */
    public void clickCheckout() {
        driver.findElement(checkoutButton).click();
    }

    /** Verifică dacă coșul este gol */
    public boolean isCartEmpty() {
        // Dacă nu găsește niciun element cu locatorul 'cartItem', coșul este gol.
        return driver.findElements(cartItem).isEmpty();
    }
}