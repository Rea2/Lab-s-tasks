package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public abstract class PO {
    protected WebDriverWait wait;
    protected final WebDriver driver;

    public PO(WebDriver driver) {
        this.driver = driver;
   //     this.wait = new WebDriverWait(this.driver, 30);
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
    protected WebElement waitUntilElementToBeClickable(WebElement webElement) {
        return new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(webElement));
    }

    protected WebElement waitUntilElementToBeVisible(WebElement webElement) {
        return new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(webElement));
    }

    protected void clickButtonWhenClickable(WebElement button){
        waitUntilElementToBeClickable(button);
        button.click();
    }

    protected void selectValueFromDropDownMenu(List<WebElement> options, String option){
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfAllElements(options));
        WebElement webElement = options.stream().filter( x -> x.getText().contains(option)).findFirst().get();
        waitUntilElementToBeClickable(webElement);
        webElement.click();
    }

    protected void clickMenuAndSelectValue(WebElement button, List<WebElement> options, String option ) {
        clickButtonWhenClickable(button);
        selectValueFromDropDownMenu(options,option);
    }
}
