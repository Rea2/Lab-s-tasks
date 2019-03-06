package pages.page_objects.cloud_google;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.page_objects.PO;

import java.util.List;

public class PO_CloudProductsCalculator extends PO {

    @FindBy (xpath = "//*[@id=\"mainForm\"]/*//md-tab-item[1]")
    private WebElement formComputeEngine;

    @FindBy (xpath = "//iframe[@id='idIframe']")
    private WebElement frame;

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



























