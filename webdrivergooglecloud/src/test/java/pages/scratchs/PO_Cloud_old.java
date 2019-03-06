package pages.scratchs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.page_objects.PO;
import pages.page_objects.cloud_google.PO_Frame;

import java.util.List;

public class PO_Cloud_old extends PO {

    @FindBy(xpath = "//div[@class = 'cloud-section-header__link']/a[@href = 'https://cloud.google.com/products/']")
    private WebElement buttonExploreAllProducts;

    @FindBy(xpath = "//a[@track-name='seePricing']")
    private WebElement buttonSeePricing;

    @FindBy(xpath = "//a[text() = 'Calculators']")
    private WebElement buttonCalculators;


    @FindBy (xpath = "//*[@id=\"mainForm\"]/*//md-tab-item[1]")
    private WebElement formComputeEngine;

    @FindBy (xpath = "//iframe[@id='idIframe']")
    private WebElement frame;

    public PO_Cloud_old(WebDriver driver) {
        super(driver);

        // Провекрка того факта, что мы на верной странице.
        if ((!driver.getTitle().equals("Google Cloud including GCP & G Suite — Try Free  |  Google Cloud")) ) {
            throw new IllegalStateException("Wrong site page!");
        }
    }

    public PO_Cloud_old clickExploreAllProducts(){
        clickButtonWhenClickable(buttonExploreAllProducts);
        return this;
    }

    public PO_Cloud_old clickSeePricing(){
        clickButtonWhenClickable(buttonSeePricing);
        return this;
    }

    public PO_Frame clickCalculators(){
        clickButtonWhenClickable(buttonCalculators);
        return PageFactory.initElements(driver, PO_Frame.class);
    }

//    public PO_Cloud waitUntilFrameAppears(){
//        new WebDriverWait(driver,10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
//        return this;
//    }


    public boolean isFormPresentForReal() {
        List<WebElement> form =  wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//form[@name = 'ComputeEngineForm']"), 1));
        if (form.size() > 0) {
            return true;
        } else return false;
    }
}



























