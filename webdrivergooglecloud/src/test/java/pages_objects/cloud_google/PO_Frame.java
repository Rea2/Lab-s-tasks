package pages_objects.cloud_google;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages_objects.PO_Abstract;
import java.util.List;

// Это Page Object представляет фрейм на странице "Google Cloud Platform Pricing Calculator"
// после выбора опции "ComputeEngine"
// адрес страницы https://cloud.google.com/products/calculator/
public class PO_Frame extends PO_Abstract {

    @FindBy(xpath = "//div[@title='Compute Engine']")
    private WebElement buttonComputeEngine;

    @FindBy(id = "input_46")
    private WebElement numberOfInstances;

    @FindBy(xpath = "//form[@name = 'ComputeEngineForm']")
    private WebElement form;

    @FindBy(id = "select_47")
    private WebElement whatAreTheseInstancesFor;

    @FindBy(id = "select_value_label_40")
    private WebElement operatingSystem;

    @FindBy(id = "select_62")
    private WebElement vmClass;

    @FindBy(id = "select_93")
    private WebElement instanceType;

    @FindBy(xpath = "//md-checkbox[@aria-label = 'Add GPUs']")
    private WebElement addGPUs;

    @FindBy(id = "select_value_label_327")
    private WebElement numberOfGPUs;

    @FindBy(id = "select_value_label_328")
    private WebElement gPUType;

    @FindBy(id = "select_value_label_43")
    private WebElement localSSD;

    @FindBy(id = "select_value_label_44")
    private WebElement dataCenterLocation;

    @FindBy(id = "select_102")
    private WebElement committedUsage;

    @FindBy(xpath = "//form[@name = 'ComputeEngineForm']//button[@aria-label = 'Add to Estimate']")
    private WebElement buttonAddToEstimate;

    @FindBy(xpath = "//div[@id='select_container_59']//md-option/div[@class = 'md-text']")
    private List<WebElement> listOptionsOperatingSystem;

    @FindBy(xpath = "//div[@id='select_container_63']//md-option/div[@class = 'md-text' ]")
    private List<WebElement> listOptionsVmClass;

    @FindBy(xpath = "//md-select-menu[@class='md-overflow']//div[@class = 'md-text']")
    private List<WebElement> listOptionsInstanceType;

    @FindBy(xpath = "//div[@id = 'select_container_330']//div[@class = 'md-text ng-binding']")
    private List<WebElement> listOptionsNumberOfGPU;

    @FindBy(xpath = "//div[@id = 'select_container_332']//div[@class = 'md-text ng-binding']")
    private List<WebElement> listOptionsGPUType;

    @FindBy(xpath = "//div[@id = 'select_container_96']//div[@class = 'md-text ng-binding']")
    private List<WebElement> listOptionsLocalSSD;

    @FindBy(xpath = "//div[@id = 'select_container_98']//div[@class = 'md-text ng-binding']")
    private List<WebElement> listOptionsDataCenterLocation;

    @FindBy(xpath = "//div[@id = 'select_container_103']//div[@class = 'md-text']")
    private List<WebElement> listOptionsCommittedUsage;

    public PO_Frame(WebDriver driver) {
        super(driver);
    }

    public PO_Frame clickComputeEngine(){
        clickButtonWhenClickable(buttonComputeEngine);
        return PageFactory.initElements(driver, PO_Frame.class);
    }

    public PO_Frame inputNumberOfInstances(String number) {
        numberOfInstances.sendKeys(number);
        return this;
    }

    public PO_Frame clickOnForm() {
        clickButtonWhenClickable(form);
        return this;
    }

    public PO_Frame selectOperatingSystem(String option)  {
        clickMenuAndSelectValue(operatingSystem, listOptionsOperatingSystem, option);
        return this;
    }

    public PO_Frame selectVmClass(String option){
        clickMenuAndSelectValue(vmClass, listOptionsVmClass, option);
        return this;
    }

    public PO_Frame selectInstantType(String option){
        clickMenuAndSelectValue(instanceType, listOptionsInstanceType,option);
        return this;
    }

    public PO_Frame tickAddGPU(){
        clickButtonWhenClickable(addGPUs);
        return this;
    }

    public PO_Frame selectNumberOfGPUs(String option){
        clickMenuAndSelectValue(numberOfGPUs, listOptionsNumberOfGPU,option);
        return this;
    }

    public PO_Frame selectGPUsType(String option){
        clickMenuAndSelectValue(gPUType, listOptionsGPUType,option);
        return this;
    }

    public PO_Frame selectLocalSsd(String option){
        clickMenuAndSelectValue(localSSD, listOptionsLocalSSD,option);
        return this;
    }

    public PO_Frame selectDataCenter(String option){
        clickMenuAndSelectValue(dataCenterLocation, listOptionsDataCenterLocation,option);
        return this;
    }

    public PO_Frame selectCommittedUsage(String option){
        clickMenuAndSelectValue(committedUsage, listOptionsCommittedUsage,option);

        return this;
    }

    public PO_Frame clickAddToEstimate() {
        clickButtonWhenClickable(buttonAddToEstimate);
        return this;
    }
}
