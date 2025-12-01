package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {

    // Am schimbat la 'protected' ca să fie accesibil în clasele care extind BaseTest
    protected WebDriver driver;

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        // **IMPORTANT: Pune URL-ul aplicației de testat aici**
        driver.get("https://www.saucedemo.com/index.html");
    }

    // NOU: Metoda obligatorie pentru a returna driverul către clasele de teste
    public WebDriver getDriver() {
        return driver;
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}