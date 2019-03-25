package com.epam.ta.page.cloud_google;

import com.epam.ta.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CloudPage extends AbstractPage {

    @FindBy(xpath = "//div[@class = 'cloud-section-header__link']/a[@href = 'https://cloud.google.com/products/']")
    private WebElement buttonExploreAllProducts;
    public static final String URL = "https://cloud.google.com/";

    public CloudPage open(){
        driver.get(URL);
        return this;
    }

    public CloudPage(WebDriver driver) {
        super(driver);
    }

    public CloudProductsPage clickExploreAllProducts(){
        clickButtonWhenClickable(buttonExploreAllProducts);
        return PageFactory.initElements(driver, CloudProductsPage.class);
    }
}



























