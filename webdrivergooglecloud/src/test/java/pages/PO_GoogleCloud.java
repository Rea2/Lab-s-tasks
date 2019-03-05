package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;



public class PO_GoogleCloud extends PO {

    @FindBy(xpath = "//div[@class = 'cloud-section-header__link']/a[@href = 'https://cloud.google.com/products/']")
    private WebElement buttonExploreAllProducts;

    @FindBy(xpath = "//a[@track-name='seePricing']")
    private WebElement buttonSeePricing;

    @FindBy(xpath = "//a[text() = 'Calculators']")
    private WebElement buttonCalculators;

    @FindBy(xpath = "//md-tabs-canvas//md-tab-item[1]")
    private WebElement buttonComputeEngine;

    @FindBy (xpath = "//*[@id=\"mainForm\"]/*//md-tab-item[1]")
    private WebElement formComputeEngine;

    @FindBy (xpath = "//iframe[@id='idIframe']")
    private WebElement frame;

    public PO_GoogleCloud(WebDriver driver) {
        super(driver);

        // Провекрка того факта, что мы на верной странице.
        if ((!driver.getTitle().equals("Google Cloud including GCP & G Suite — Try Free  |  Google Cloud")) ) {
            throw new IllegalStateException("Wrong site page!");
        }
    }

    public PO_GoogleCloud clickExploreAllProducts(){
        buttonExploreAllProducts.click();
        return this;
    }

    public PO_GoogleCloud clickSeePricing(){
        waitUntilElementToBeClickable(buttonSeePricing);
        buttonSeePricing.click();
        return this;
    }

    public PO_GoogleCloud clickCalculators(){
        waitUntilElementToBeClickable(buttonCalculators);
        buttonCalculators.click();
        return this;
    }

    public PO_Frame clickComputeEngine(){
        waitUntilElementToBeClickable(buttonComputeEngine);
        buttonComputeEngine.click();
 //       new WebDriverWait(driver,10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
        return PageFactory.initElements(driver, PO_Frame.class);
    }

    public boolean isFormPresentForReal() {
        List<WebElement> form =  wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//form[@name = 'ComputeEngineForm']"), 1));
        if (form.size() > 0) {
            return true;
        } else return false;
    }


























}
