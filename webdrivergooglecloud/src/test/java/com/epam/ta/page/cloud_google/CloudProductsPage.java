package com.epam.ta.page.cloud_google;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.epam.ta.page.AbstractPage;

public class CloudProductsPage extends AbstractPage {

    @FindBy(xpath = "//a[@track-name='seePricing']")
    private WebElement buttonSeePricing;

    public CloudProductsPage(WebDriver driver) {
        super(driver);
    }

    public CloudPricingPage clickSeePricing(){
        clickButtonWhenClickable(buttonSeePricing);
        return PageFactory.initElements(driver, CloudPricingPage.class);
    }
}



























