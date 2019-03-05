package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PO_10minuteEmail extends PO {

    @FindBy(xpath = "//input[@id='mailAddress']")
    private WebElement inputEmailAddress;

    @FindBy(xpath = "//span[@class='ui-accordion-header-icon ui-icon ui-icon-triangle-1-e']")
    private WebElement showEmailContent;

    @FindBy(xpath = "//table[@class = 'quote']//tr[3]/td[2]/h3")
    private WebElement costValue;


    public PO_10minuteEmail(WebDriver driver) {
        super(driver);
    }

    public String getTextFromInputEmailAddress() {
        new WebDriverWait(driver,10).until(ExpectedConditions.textToBePresentInElementValue(inputEmailAddress, "value"));
        return inputEmailAddress.getAttribute("value");
    }

    public PO_10minuteEmail sd() {
        waitUntilElementToBeClickable(showEmailContent);
        showEmailContent.click();
        return this;
    }

    public String getTextCostValueFromEmail(){
        waitUntilElementToBeVisible(costValue);
        return costValue.getText();
    }


}











