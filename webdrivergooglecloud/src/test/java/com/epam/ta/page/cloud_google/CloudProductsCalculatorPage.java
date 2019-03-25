package com.epam.ta.page.cloud_google;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.epam.ta.page.AbstractPage;

import java.util.List;

public class CloudProductsCalculatorPage extends AbstractPage {

    @FindBy (xpath = "//iframe[@id='idIframe']")
    private WebElement frame;

    @FindBy (xpath = "//iframe[@id='idIframe']")
    private WebElement form;

    public CloudProductsCalculatorPage(WebDriver driver) {
        super(driver);
    }
    public boolean isFormPresentForReal() {
        List<WebElement> form =  wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//form[@name = 'ComputeEngineForm']"), 1));
        if (form.size() > 0) {
            return true;
        } else return false;
    }

    public CloudProductsCalculatorPage waitUntilFrameVisible(){
        waitUntilElementToBeVisible(frame);
        return this;
    }
}



























