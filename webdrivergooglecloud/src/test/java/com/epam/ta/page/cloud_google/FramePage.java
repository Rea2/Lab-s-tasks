package com.epam.ta.page.cloud_google;

import com.epam.ta.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

// Это Page Object представляет фрейм на странице "Google Cloud Platform Pricing Calculator"
// после выбора опции "ComputeEngine"
// адрес страницы https://cloud.google.com/products/calculator/
public class FramePage extends AbstractPage {

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

    public FramePage(WebDriver driver) {
        super(driver);
    }

    public FramePage clickComputeEngine(){
        clickButtonWhenClickable(buttonComputeEngine);
        return PageFactory.initElements(driver, FramePage.class);
    }

    public FramePage inputNumberOfInstances(String number) {
        numberOfInstances.sendKeys(number);
        return this;
    }

    public FramePage clickOnForm() {
        clickButtonWhenClickable(form);
        return this;
    }

    public FramePage selectOperatingSystem(String option)  {
        clickMenuAndSelectValue(operatingSystem, listOptionsOperatingSystem, option);
        return this;
    }

    public FramePage selectVmClass(String option){
        clickMenuAndSelectValue(vmClass, listOptionsVmClass, option);
        return this;
    }

    public FramePage selectInstantType(String option){
        clickMenuAndSelectValue(instanceType, listOptionsInstanceType,option);
        return this;
    }

    public FramePage tickAddGPU(){
        clickButtonWhenClickable(addGPUs);
        return this;
    }

    public FramePage selectNumberOfGPUs(String option){
        clickMenuAndSelectValue(numberOfGPUs, listOptionsNumberOfGPU,option);
        return this;
    }

    public FramePage selectGPUsType(String option){
        clickMenuAndSelectValue(gPUType, listOptionsGPUType,option);
        return this;
    }

    public FramePage selectLocalSsd(String option){
        clickMenuAndSelectValue(localSSD, listOptionsLocalSSD,option);
        return this;
    }

    public FramePage selectDataCenter(String option){
        clickMenuAndSelectValue(dataCenterLocation, listOptionsDataCenterLocation,option);
        return this;
    }

    public FramePage selectCommittedUsage(String option){
        clickMenuAndSelectValue(committedUsage, listOptionsCommittedUsage,option);

        return this;
    }

    public FramePage clickAddToEstimate() {
        clickButtonWhenClickable(buttonAddToEstimate);
        return this;
    }


}
