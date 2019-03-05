package pages;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PO_Frame extends PO {

    @FindBy(xpath = "//input[@id = 'input_46']")
    private WebElement numberOfInstances;

    @FindBy(xpath = "//input[@id = 'input_47']")
    private WebElement whatAreTheseInstancesFor;

    @FindBy(xpath = "//md-select[@id = 'select_58']")
    private WebElement operatingSystem;

    // "Free: Debian, CentOS, CoreOS, Ubuntu, or other User Provided OS"
    @FindBy(xpath = "//md-option[@id = 'select_option_48']/div")
    private WebElement operatingSystemOption1;

    // "Regular"
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

    @FindBy(xpath = "//div[@id='select_container_59']//md-option/div")
    private List<WebElement> listOptionsOperatingSystem;

    @FindBy(xpath = "//div[@id='select_container_63']//md-option/div[@class = 'md-text' ]")
    private List<WebElement> listOptionsVmClass;

    @FindBy(xpath = "//md-select-menu[@class='md-overflow']//div[@class = 'md-text']")
    private List<WebElement> listOptionsInstanceType;

    @FindBy(xpath = "//div[@id = 'select_container_331']//div[@class = 'md-text ng-binding']")
    private List<WebElement> listOptionsNumberOfGPU;


    @FindBy(xpath = "//div[@id = 'select_container_333']//div[@class = 'md-text ng-binding']")
    private List<WebElement> listOptionsGPUType;

    @FindBy(xpath = "//div[@id = 'select_container_96']//div[@class = 'md-text ng-binding']")
    private List<WebElement> listOptionsLocalSSD;

    @FindBy(xpath = "//div[@id = 'select_container_113']//div[@class = 'md-text ng-binding']")
    private List<WebElement> listOptionsDataCenterLocation;

    @FindBy(xpath = "")
    private List<WebElement> listOptionsCommittedUsage;

    public PO_Frame(WebDriver driver) {
        super(driver);
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

    public PO_Frame selectOperatingSystem()  {
        clickMenuAndSelectValue(operatingSystem, listOptionsOperatingSystem,
                "Free: Debian, CentOS, CoreOS, Ubuntu, or other User Provided OS" );
        return this;
    }

    public PO_Frame selectVmClass(){
        clickMenuAndSelectValue(vmClass, listOptionsVmClass,
                "Regular" );
        return this;
    }

    public PO_Frame selectInstantType(){
        clickMenuAndSelectValue(instanceType, listOptionsInstanceType,
                "n1-standard-8    (vCPUs: 8, RAM: 30 GB)" );
        return this;
    }

    public PO_Frame tickAddGPU(){
        clickButtonWhenClickable(addGPUs);
        return this;
    }

    public PO_Frame selectNumberOfGPUs(){
        clickMenuAndSelectValue(numberOfGPUs, listOptionsNumberOfGPU,
                "1" );
        return this;
    }

    public PO_Frame selectGPUsType(){
        clickMenuAndSelectValue(gPUType, listOptionsGPUType,
                "NVIDIA Tesla V100" );
        return this;
    }

    public PO_Frame selectLocalSsd(){
        clickMenuAndSelectValue(localSSD, listOptionsLocalSSD,
                "2x375 GB" );
        return this;
    }

    public PO_Frame selectDataCenter(){
        waitUntilElementToBeClickable(dataCenterLocation);
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

    public PO_Frame clickAddToEstimate() {
        new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(buttonAddToEstimate));
        buttonAddToEstimate.click();
        return this;
    }
















}
