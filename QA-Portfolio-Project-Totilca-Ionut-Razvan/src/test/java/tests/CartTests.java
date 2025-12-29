package tests;

import base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.*;

public class CartTests extends BaseTest {

    @Test
    public void testCompletePurchaseFlow() {
        // Inițializăm toate paginile necesare
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        // Pas 1: Login
        loginPage.login("standard_user", "secret_sauce");

        // Pas 2: Adăugăm produs în coș și mergem la coș
        productsPage.addBackpackToCart();
        productsPage.goToCart();

        // Pas 3: Verificăm dacă produsul corect este în coș
        Assertions.assertEquals("Sauce Labs Backpack", cartPage.getItemName());
        cartPage.clickCheckout();

        // Pas 4: Finalizăm comanda
        checkoutPage.fillInformation("Ionut", "Razvan", "12345");
        checkoutPage.finishOrder();

        // Pas 5: Validarea finală
        Assertions.assertEquals("Thank you for your order!", checkoutPage.getSuccessMessage());
    }
}