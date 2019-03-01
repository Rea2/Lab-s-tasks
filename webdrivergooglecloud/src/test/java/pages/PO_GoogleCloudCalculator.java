package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PO_GoogleCloudCalculator extends PO {

//    @FindBy(xpath = "//div[@class = 'cloud-section-header__link']/a[@href = 'https://cloud.google.com/products/']")
//    private WebElement buttonExploreAllProducts;
//
//    @FindBy(xpath = "//a[@track-name='seePricing']")
//    private WebElement buttonSeePricing;
//
//    @FindBy(xpath = "//a[text() = 'Calculators']")
//    private WebElement buttonCalculators;
//
//    @FindBy(xpath = "//div[@class = 'tab-holder compute' ][@title = 'Compute Engine']")
//    private WebElement buttonComputeEngine;
//
//    @FindBy (xpath = "//form[@name = 'ComputeEngineForm']")
//    private WebElement formComputeEngine;



//

    public PO_GoogleCloudCalculator(WebDriver driver) {
        super(driver);
        // Провекрка того факта, что мы на верной странице.
        if ((!driver.getTitle().equals("GCP Pricing  |  Google Cloud")) ) {
            throw new IllegalStateException("Wrong site page!");
        }
    }
















}
