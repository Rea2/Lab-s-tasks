package com.epam.ta.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public abstract class AbstractPage {
    protected WebDriverWait wait;
    protected final WebDriver driver;
    private static final int WAIT_FOR_ELEMENT_TIMEOUT_SECONDS = 8;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected WebElement waitUntilElementToBeClickable(WebElement webElement) {
        return new WebDriverWait(driver,WAIT_FOR_ELEMENT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(webElement));
    }

    protected WebElement waitUntilElementToBeVisible(WebElement webElement) {
        return new WebDriverWait(driver,WAIT_FOR_ELEMENT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOf(webElement));
    }

    protected void clickButtonWhenClickable(WebElement button){
        waitUntilElementToBeClickable(button);
        new Actions(driver).click(button).build().perform();
    }

    protected void selectValueFromDropDownMenu(List<WebElement> options, String option){
            new WebDriverWait(driver,WAIT_FOR_ELEMENT_TIMEOUT_SECONDS).
                    until(ExpectedConditions.visibilityOfAllElements(options));
        WebElement webElement = options.stream().filter( x -> x.getText().contains(option)).findFirst().get();
        clickButtonWhenClickable(webElement);
    }

    protected void clickMenuAndSelectValue(WebElement button, List<WebElement> options, String option ) {
        waitUntilElementToBeClickable(button);
        new Actions(driver).click(button).build().perform();
        selectValueFromDropDownMenu(options,option);
    }
}
