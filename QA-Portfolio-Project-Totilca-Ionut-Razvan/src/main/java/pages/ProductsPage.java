package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class ProductsPage {
    private WebDriver driver;

    // 1. Locatori (Elemente)
    // Listă de produse (containerul care le include pe toate)
    private By productListContainer = By.className("inventory_list");
    // Element individual produs (folosit pentru a găsi toate produsele)
    private By productItem = By.className("inventory_item");
    // Titlul unui produs (folosit pentru a deschide un produs specific)
    private By productTitle = By.className("inventory_item_name");
    // Dropdown pentru sortare (Filter products)
    private By sortDropdown = By.className("product_sort_container");
    // Buton de Adăugare în Coș (specific fiecărui produs, dar putem folosi un locator general)
    private By addToCartButton = By.xpath("//button[text()='Add to cart']");

    // **Locator pentru un produs specific după nume**
    public By getProductLinkByName(String productName) {
        return By.xpath("//div[@class='inventory_item_name' and text()='" + productName + "']");
    }

    // Constructor
    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    // 2. Acțiuni (Metode)

    /** TC-101: Vizualizare produse */
    public List<WebElement> getAllProducts() {
        // Returnează lista tuturor elementelor produs din container
        return driver.findElements(productItem);
    }

    /** TC-102: Deschide un produs specific */
    public void openProductByName(String productName) {
        // Clic pe linkul/titlul produsului
        driver.findElement(getProductLinkByName(productName)).click();
    }

    /** TC-103: Aplică un filtru */
    public void selectFilterOption(String visibleText) {
        WebElement dropdownElement = driver.findElement(sortDropdown);
        Select select = new Select(dropdownElement);
        // Selectează opțiunea de sortare (ex: Name (Z to A), Price (low to high))
        select.selectByVisibleText(visibleText);
    }

    /** Ajutor: Adaugă primul produs din listă în coș */
    public void addFirstProductToCart() {
        // Găsește primul buton "Add to cart" și apasă-l
        driver.findElement(addToCartButton).click();
    }
}