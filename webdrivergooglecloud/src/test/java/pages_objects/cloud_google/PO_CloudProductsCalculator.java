package pages_objects.cloud_google;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages_objects.PO_Abstract;

import java.util.List;

public class PO_CloudProductsCalculator extends PO_Abstract {

    @FindBy (xpath = "//iframe[@id='idIframe']")
    private WebElement frame;

    @FindBy (xpath = "//iframe[@id='idIframe']")
    private WebElement form;

    public PO_CloudProductsCalculator(WebDriver driver) {
        super(driver);
    }
    public boolean isFormPresentForReal() {
        List<WebElement> form =  wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//form[@name = 'ComputeEngineForm']"), 1));
        if (form.size() > 0) {
            return true;
        } else return false;
    }

    public PO_CloudProductsCalculator waitUntilFrameVisible(){
        waitUntilElementToBeVisible(frame);
        return this;
    }
}



























