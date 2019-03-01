package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PO_Frame extends PO {

    @FindBy(xpath = "//input[@id = 'input_46']")
    private WebElement numberOfInstances;

    @FindBy(xpath = "//input[@id = 'input_47']")
    private WebElement whatAreTheseInstancesFor;

    @FindBy(xpath = "//md-select[@id = 'select_58']")
    private WebElement operatingSystem;

    @FindBy(xpath = "//md-option[@id = 'select_option_48']/div")
    private WebElement operatingSystemOption1;

    @FindBy(xpath = "//md-select[@id = 'select_62']")
    private WebElement vmClass;

    @FindBy(xpath = "//md-option [@id = 'select_option_60']")
    private WebElement vmClassOption1;

    @FindBy(xpath = "//md-select[@id = 'select_93']")
    private WebElement instanceType;

    @FindBy(xpath = "//md-option [@id = 'select_option_70']")
    private WebElement instanceTypeOptional1;

    @FindBy(xpath = "//md-checkbox[@aria-label = 'Add GPUs']")
    private WebElement addGPUs;

    @FindBy(xpath = "//md-select-value[@id = 'select_value_label_327']")
    private WebElement numberOfGPUs;

    @FindBy(xpath = "//md-option[@id = 'select_option_334']")
    private WebElement numberOfGPUsOption1;

    @FindBy(xpath = "//md-select-value[@id ='select_value_label_328']")
    private WebElement gPUType;

    @FindBy(xpath = "//md-option[@id = 'select_option_341']")
    private WebElement gPUTypeOption1;

    @FindBy(xpath = "//md-select-value[@id = 'select_value_label_43']")
    private WebElement localSSD;

    @FindBy(xpath = "//md-option[@id = 'select_option_182']")
    private WebElement localSSDOption1;

    @FindBy(xpath = "//md-select-value[@id = 'select_value_label_44']")
    private WebElement dataCenterLocation;

    @FindBy(xpath = "//md-option[@id = 'select_option_196']")
    private WebElement dataCenterLocationOption1;

    @FindBy(xpath = "//md-select[@id = 'select_102']")
    private WebElement committedUsage;

    @FindBy(xpath = "//md-option[@id = 'select_option_100']")
    private WebElement committedUsageOption1;

    @FindBy(xpath = "//form[@name = 'ComputeEngineForm']//button[@aria-label = 'Add to Estimate']")
    private WebElement buttonAddToEstimate;

    public PO_Frame(WebDriver driver) {
        super(driver);
        // Провекрка того факта, что мы на верной странице.
//        if ((!driver.getTitle().equals("Cloud Pricing Calculator")) ) {
//            throw new IllegalStateException("Wrong site page!");
//        }
    }

    public WebElement waitUntilElementToBeClickable(WebElement webElement) {
        return new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public boolean isFormPresentForReal() {
        List<WebElement> form =  wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//form[@name = 'ComputeEngineForm']"), 1));
        if (form.size() > 0) {
            return true;
        } else return false;
    }

    public PO_Frame inputNumberOfInstances(String number) {
        numberOfInstances.sendKeys(number);
        return this;
    }

// "Free: Debian, CentOS, CoreOS, Ubuntu, or other User Provided OS"
    public PO_Frame selectOperatingSystem()  {
        operatingSystem.click();
        waitUntilElementToBeClickable(operatingSystemOption1);
        operatingSystemOption1.click();
        return this;
    }
//"Regular"
    public PO_Frame selectVmClass(){
        vmClass.click();
        waitUntilElementToBeClickable(vmClassOption1);
        vmClassOption1.click();
        return this;
    }

    public PO_Frame selectInstantType(){
        instanceType.click();
        waitUntilElementToBeClickable(instanceTypeOptional1);
        instanceTypeOptional1.click();
        return this;
    }

    public PO_Frame tickAddGPU(){
        addGPUs.click();
        return this;
    }

    public PO_Frame selectNumberOfGPUs(){
        waitUntilElementToBeClickable(numberOfGPUs);
        numberOfGPUs.click();
        waitUntilElementToBeClickable(numberOfGPUsOption1);
        numberOfGPUsOption1.click();
        return this;
    }

    public PO_Frame selectGPUsType(){
        gPUType.click();
        waitUntilElementToBeClickable(gPUTypeOption1);
        gPUTypeOption1.click();
        return this;
    }

    public PO_Frame selectLocalSsd(){
        localSSD.click();
        waitUntilElementToBeClickable(localSSDOption1);
        localSSDOption1.click();
        return this;
    }

    public PO_Frame selectDataCenter(){
        dataCenterLocation.click();
        waitUntilElementToBeClickable(dataCenterLocationOption1);
        dataCenterLocationOption1.click();
        return this;
    }

    public PO_Frame selectCommittedUsage(){
        committedUsage.click();
        waitUntilElementToBeClickable(committedUsageOption1);
        committedUsageOption1.click();
        return this;
    }

    public PO_Frame clickAddToEstimate() throws InterruptedException {
        new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(buttonAddToEstimate));
        buttonAddToEstimate.click();
        return this;
    }










}
