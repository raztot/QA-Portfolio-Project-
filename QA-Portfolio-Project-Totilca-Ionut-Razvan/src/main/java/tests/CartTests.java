package tests;

import base.BaseTest;
import pages.CartPage;
import pages.CheckoutPage; // NOU
import pages.LoginPage;
import pages.ProductsPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.*;

public class CartTests extends BaseTest {

    private LoginPage loginPage;
    private ProductsPage productsPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage; // NOU

    private final String VALID_USER = "standard_user";
    private final String VALID_PASS = "secret_sauce";

    // Date de test pentru checkout
    private final String FIRST_NAME = "Tester";
    private final String LAST_NAME = "Automation";
    private final String ZIP_CODE = "90210";

    @BeforeEach
    public void setupCartPage() {
        // 1. Inițializează Page Objects
        loginPage = new LoginPage(getDriver());
        productsPage = new ProductsPage(getDriver());
        cartPage = new CartPage(getDriver());
        checkoutPage = new CheckoutPage(getDriver()); // NOU

        // 2. Precondiție 1: Login
        loginPage.login(VALID_USER, VALID_PASS);

        // 3. Precondiție 2: Adaugă un produs în coș
        // Ne asigurăm că există un produs adăugat în coș pentru a începe checkout-ul
        if (getDriver().findElements(By.className("shopping_cart_badge")).isEmpty()) {
            productsPage.addFirstProductToCart();
        }

        // 4. Precondiție 3: Navighează la pagina Cart
        getDriver().findElement(By.className("shopping_cart_link")).click();

        assertTrue(getDriver().getCurrentUrl().contains("/cart.html"),
                "Precondiție eșuată: Nu s-a putut ajunge pe pagina Cart.");
    }

    // ... (TC-201 și TC-202 rămân neschimbate) ...

    // TC-203: Successful checkout
    @Test
    public void successfulCheckoutTest() {
        // Pas 1: Navigare la Checkout
        cartPage.clickCheckout();
        assertTrue(getDriver().getCurrentUrl().contains("/checkout-step-one.html"),
                "Eșuat: Nu s-a ajuns la prima etapă de checkout.");

        // Pas 2: Completarea informațiilor client și continuare
        checkoutPage.enterCheckoutInformation(FIRST_NAME, LAST_NAME, ZIP_CODE);
        assertTrue(getDriver().getCurrentUrl().contains("/checkout-step-two.html"),
                "Eșuat: Nu s-a ajuns la a doua etapă de sumar.");

        // Pas 3: Finalizarea comenzii
        checkoutPage.clickFinish();

        // Rezultat Așteptat: Order confirmed (Verificăm mesajul de confirmare)
        String expectedMessage = "Thank you for your order!";
        assertEquals(expectedMessage, checkoutPage.getThankYouMessage(),
                "Eșuat: Mesajul de confirmare a comenzii nu corespunde.");

        // Verificare suplimentară: URL-ul de confirmare
        assertTrue(getDriver().getCurrentUrl().contains("/checkout-complete.html"),
                "Eșuat: Nu s-a ajuns la pagina de confirmare.");
    }
}