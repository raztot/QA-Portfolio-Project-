package tests;

import base.BaseTest;
import pages.LoginPage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTests extends BaseTest {

    private LoginPage loginPage;

    // Date de test (le poți muta într-un fișier separat sau le poți lăsa aici pentru simplitate)
    private final String VALID_USER = "standard_user";
    private final String VALID_PASS = "secret_sauce";

    @BeforeEach
    public void initializePages() {
        // Obține driverul configurat în BaseTest
        loginPage = new LoginPage(getDriver());
    }

    // TC-001: Valid login
    @Test
    public void validLoginTest() {
        // Pas: Enter valid email & password -> Login
        loginPage.login(VALID_USER, VALID_PASS);

        // Rezultat Așteptat: User logs in (Verifică redirecționarea la pagina de produse)
        // **IMPORTANT: Schimbă cu URL-ul paginii de produse**
        assertTrue(getDriver().getCurrentUrl().endsWith("/inventory.html"), "Eșuat: Nu s-a logat cu succes.");
    }

    // TC-002: Invalid password
    @Test
    public void invalidPasswordTest() {
        // Pas: Valid email + wrong pass
        loginPage.login(VALID_USER, "wrong_password");

        // Rezultat Așteptat: Error message
        String expectedError = "Epic sadface: Username and password do not match any user in this service"; // Ajustează mesajul!
        assertEquals(expectedError, loginPage.getErrorMessage(), "Eșuat: Mesajul de eroare nu corespunde.");
    }

    // TC-003: Invalid email format
    @Test
    public void invalidEmailFormatTest() {
        // Pentru aplicațiile care nu folosesc validare HTML5 sau care folosesc câmpuri tip `text` fără validare pe client,
        // acest test poate necesita un input care declanșează validarea reală.
        // Presupunând o validare de bază la nivel de câmp:

        // Pas: Wrong email syntax (Folosim un input invalid ex: "test@")
        loginPage.enterEmail("test@");
        loginPage.enterPassword(VALID_PASS);
        loginPage.clickLoginButton();

        // Dacă aplicația folosește validare standard de browser (HTML5 type="email"), putem verifica altfel.
        // Pentru o validare la nivel de sistem:

        // Rezultat Așteptat: Validation error (Verificăm dacă apare un mesaj de eroare)
        assertFalse(loginPage.getErrorMessage().isEmpty(), "Eșuat: Nu a apărut mesajul de eroare de validare.");

        // **Notă:** Acest test depinde foarte mult de **modul în care aplicația validează formatul emailului**.
        // Dacă e validare de browser, s-ar putea să nu funcționeze.
    }
}