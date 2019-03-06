package pages.page_objects.tenminutesmail;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.page_objects.PO;

public class PO_10minuteEmail extends PO {

    @FindBy(xpath = "//input[@id='mailAddress']")
    private WebElement inputEmailAddress;

    @FindBy(xpath = "//span[@class='ui-accordion-header-icon ui-icon ui-icon-triangle-1-e']")
    private WebElement showEmailContent;

    @FindBy(xpath = "//table[@class = 'quote']//tr[2]/td[2]/h3")
    private WebElement costValue;

    public PO_10minuteEmail(WebDriver driver) {
        super(driver);
    }

    public String getTextFromInputEmailAddress() {
        waitUntilElementToBeVisible(waitUntilElementToBeVisible(inputEmailAddress));
        return inputEmailAddress.getAttribute("value");
    }

    public PO_10minuteEmail openEmailFromGoogleCloud() {
        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(showEmailContent));
        showEmailContent.click();
        return this;
    }

    public String getTextCostValueFromEmail(){
        waitUntilElementToBeVisible(costValue);
        return costValue.getText();
    }


}











