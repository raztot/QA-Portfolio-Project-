package tests;

import base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.LoginPage;

public class LoginTests extends BaseTest {

    @Test
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);

        // Pasul 1: Introducem date corecte
        loginPage.login("standard_user", "secret_sauce");

        // Pasul 2: Verificăm că am ajuns pe pagina de produse
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertTrue(currentUrl.contains("inventory.html"), "Login-ul nu a reușit!");
    }

    @Test
    public void testInvalidPasswordLogin() {
        LoginPage loginPage = new LoginPage(driver);

        // Pasul 1: Introducem parolă greșită
        loginPage.login("standard_user", "parola_gresita");

        // Pasul 2: Verificăm mesajul de eroare (Negative Testing)
        String error = loginPage.getErrorMessageText();
        Assertions.assertTrue(error.contains("Username and password do not match"), "Mesajul de eroare lipsește!");
    }
}