package pages.page_objects.cloud_google;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.page_objects.PO;

public class PO_CloudPricing extends PO {

    @FindBy(xpath = "//a[text() = 'Calculators']")
    private WebElement buttonCalculators;

    public PO_CloudPricing(WebDriver driver) {
        super(driver);
    }

    public PO_CloudProductsCalculator clickCalculators(){
        clickButtonWhenClickable(buttonCalculators);
        return PageFactory.initElements(driver, PO_CloudProductsCalculator.class);
    }
}



























