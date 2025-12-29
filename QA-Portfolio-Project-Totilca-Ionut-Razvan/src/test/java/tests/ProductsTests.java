package tests;

import base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.ProductsPage;

public class ProductsTests extends BaseTest {

    @Test
    public void testAddBackpackToCart() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);

        // Pas 1: Login (necesar pentru a ajunge la produse)
        loginPage.login("standard_user", "secret_sauce");

        // Pas 2: Adăugare în coș folosind noua metodă
        productsPage.addBackpackToCart();

        // Pas 3: Navigare la coș
        productsPage.goToCart();

        // Pas 4: Verificare (Assertion)
        // Verificăm dacă URL-ul s-a schimbat către pagina de coș
        Assertions.assertTrue(driver.getCurrentUrl().contains("cart.html"),
                "Utilizatorul nu a ajuns în pagina de coș!");
    }
}