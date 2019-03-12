package pages_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public abstract class PO_Abstract {
    protected WebDriverWait wait;
    protected final WebDriver driver;
    private static final int WAIT_FOR_ELEMENT_TIMEOUT_SECONDS = 8;

    public PO_Abstract(WebDriver driver) {
        this.driver = driver;
    }

    protected void checkTitlePage(String titleExpected) {
        if ((!driver.getTitle().equals(titleExpected)) ) {
            throw new IllegalStateException("Wrong site page!");
        }
    }

    // Универсальный  Explicit wait для ожидания, что веб-элемент webElement кликабельный
    protected WebElement waitUntilElementToBeClickable(WebElement webElement) {
        return new WebDriverWait(driver,WAIT_FOR_ELEMENT_TIMEOUT_SECONDS).until(ExpectedConditions.elementToBeClickable(webElement));
    }

    protected WebElement waitUntilElementToBeVisible(WebElement webElement) {
        return new WebDriverWait(driver,WAIT_FOR_ELEMENT_TIMEOUT_SECONDS).until(ExpectedConditions.visibilityOf(webElement));
    }

    protected void clickButtonWhenClickable(WebElement button){
        waitUntilElementToBeClickable(button);
        button.click();
    }

    protected void selectValueFromDropDownMenu(List<WebElement> options, String option){
        new WebDriverWait(driver,WAIT_FOR_ELEMENT_TIMEOUT_SECONDS).until(ExpectedConditions.visibilityOfAllElements(options));
        WebElement webElement = options.stream().filter( x -> x.getText().contains(option)).findFirst().get();
        waitUntilElementToBeClickable(webElement);
        webElement.click();
    }

    protected void clickMenuAndSelectValue(WebElement button, List<WebElement> options, String option ) {
        clickButtonWhenClickable(button);
        selectValueFromDropDownMenu(options,option);
    }
}
