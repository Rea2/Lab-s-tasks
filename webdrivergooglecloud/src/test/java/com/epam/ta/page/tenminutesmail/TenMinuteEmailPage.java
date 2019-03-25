package com.epam.ta.page.tenminutesmail;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.epam.ta.page.AbstractPage;

public class TenMinuteEmailPage extends AbstractPage {

    @FindBy(xpath = "//input[@id='mailAddress']")
    private WebElement inputEmailAddress;

    @FindBy(xpath = "//span[@class='ui-accordion-header-icon ui-icon ui-icon-triangle-1-e']")
    private WebElement showEmailContent;

    @FindBy(xpath = "//table[@class = 'quote']//tr[2]/td[2]/h3")
    private WebElement costValue;

    public TenMinuteEmailPage(WebDriver driver) {
        super(driver);
    }

    public String getTextFromInputEmailAddress() {
        waitUntilElementToBeVisible(waitUntilElementToBeVisible(inputEmailAddress));
        return inputEmailAddress.getAttribute("value");
    }

    public TenMinuteEmailPage openEmailFromGoogleCloud() {
        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(showEmailContent));
        showEmailContent.click();
        return this;
    }

    public String getTextCostValueFromEmail(){
        waitUntilElementToBeVisible(costValue);
        return costValue.getText();
    }


}











