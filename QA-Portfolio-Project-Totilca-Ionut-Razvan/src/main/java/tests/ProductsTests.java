package tests;
package tests;

import base.BaseTest;         // Import BaseTest
import pages.LoginPage;         // Import LoginPage
import pages.ProductsPage;      // Import ProductsPage

import org.openqa.selenium.By; // NOU - Necesită import pentru By
import org.openqa.selenium.WebElement; // NOU - Necesită import pentru WebElement

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// ... restul codului ...
public class ProductsTests extends BaseTest {

    private LoginPage loginPage;
    private ProductsPage productsPage;

    // Date de test (asigură-te că te loghezi)
    private final String VALID_USER = "standard_user";
    private final String VALID_PASS = "secret_sauce";

    // Nume de produs real din aplicația ta (ex: "Sauce Labs Backpack")
    private final String TEST_PRODUCT_NAME = "Sauce Labs Backpack";

    @BeforeEach
    public void setupProductPage() {
        // 1. Inițializează Page Objects
        loginPage = new LoginPage(getDriver());
        productsPage = new ProductsPage(getDriver());

        // 2. Precondiție: Asigură-te că ești logat și pe pagina de produse
        loginPage.login(VALID_USER, VALID_PASS);
        // Verifică dacă ești pe pagina corectă, în caz că BaseTest nu o deschide direct
        assertTrue(getDriver().getCurrentUrl().contains("/inventory.html"),
                "Precondiție eșuată: Nu s-a putut ajunge pe pagina de produse.");
    }

    // TC-101: View products
    @Test
    public void viewProductsTest() {
        // Pas: Open Products page (Precondiția asigură deja că suntem acolo)

        // Rezultat Așteptat: Products listed (Verificăm dacă există cel puțin un produs)
        int productCount = productsPage.getAllProducts().size();
        assertTrue(productCount > 0, "Eșuat: Nu au fost găsite produse pe pagină.");
    }

    // TC-102: Open product
    @Test
    public void openProductTest() {
        // Pas: Click an item
        productsPage.openProductByName(TEST_PRODUCT_NAME);

        // Rezultat Așteptat: Details shown (Verificăm dacă URL-ul s-a schimbat la pagina de detalii a produsului)
        String expectedUrlPart = "/inventory-item.html";
        assertTrue(getDriver().getCurrentUrl().contains(expectedUrlPart),
                "Eșuat: Nu s-a ajuns pe pagina de detalii a produsului.");
        // De asemenea, putem verifica dacă numele produsului este afișat pe pagina de detalii
        assertTrue(getDriver().getPageSource().contains(TEST_PRODUCT_NAME),
                "Eșuat: Numele produsului nu este afișat pe pagina de detalii.");
    }

    // TC-103: Filter products
    @Test
    public void filterProductsTest() {
        // Pas: Apply filter (Sortare de la Z la A)
        productsPage.selectFilterOption("Name (Z to A)");

        // Rezultat Așteptat: List updates (Verificăm dacă primul element din listă s-a schimbat)
        // După sortarea Z-A, primul produs ar trebui să fie cel cu litera Z (sau ultimul alfabetic).
        // Presupunând că ultimul produs alfabetic se termină cu 'T-Shirt (Red)'

        // A. Obținem primul produs după sortare
        WebElement firstProductAfterFilter = productsPage.getAllProducts().get(0).findElement(By.className("inventory_item_name"));
        String firstProductName = firstProductAfterFilter.getText();

        // B. Verificăm ordinea (Ajustează 'Expected' în funcție de aplicația testată!)
        // Exemplu: În Swag Labs, "Test.allTheThings() T-Shirt (Red)" ar fi primul.
        String expectedFirstProduct = "Test.allTheThings() T-Shirt (Red)";

        assertEquals(expectedFirstProduct, firstProductName,
                "Eșuat: Lista de produse nu a fost sortată corect după Name (Z to A).");
    }
}