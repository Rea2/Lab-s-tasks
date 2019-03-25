package com.epam.ta.page.cloud_google;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.epam.ta.page.AbstractPage;

public class CloudPricingPage extends AbstractPage {

    @FindBy(xpath = "//a[text() = 'Calculators']")
    private WebElement buttonCalculators;

    public CloudPricingPage(WebDriver driver) {
        super(driver);
    }

    public CloudProductsCalculatorPage clickCalculators(){
        clickButtonWhenClickable(buttonCalculators);
        return PageFactory.initElements(driver, CloudProductsCalculatorPage.class);
    }
}



























