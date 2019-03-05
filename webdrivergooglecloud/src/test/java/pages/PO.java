package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PO {
    protected WebDriverWait wait;
    protected final WebDriver driver;

    public PO(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 30);
    }

    protected boolean isWebElementExist (WebElement webElement) {
        try {
            webElement.isEnabled();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // Универсальный  Explicit wait для ожидания, что веб-элемент webElement кликабельный
    public WebElement waitUntilElementToBeClickable(WebElement webElement) {
        return new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public WebElement waitUntilElementToBeVisible(WebElement webElement) {
        return new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(webElement));
    }
}
