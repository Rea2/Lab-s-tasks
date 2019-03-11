package pages_objects.cloud_google;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages_objects.PO;

public class PO_CloudProducts extends PO {

    @FindBy(xpath = "//a[@track-name='seePricing']")
    private WebElement buttonSeePricing;

    public PO_CloudProducts(WebDriver driver) {
        super(driver);
    }

    public PO_CloudPricing clickSeePricing(){
        clickButtonWhenClickable(buttonSeePricing);
        return PageFactory.initElements(driver, PO_CloudPricing.class);
    }
}



























