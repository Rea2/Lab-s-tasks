package com.epam.ta.page.cloud_google;

import com.epam.ta.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FormEmailPage extends AbstractPage {

    @FindBy(xpath = "//input[@id='input_380']")
    private WebElement eMail;

    @FindBy(xpath = "//button[@aria-label='Send Email']")
    private WebElement sendEmail;

    @FindBy(xpath = "//form[@name =  'emailForm']")
    private WebElement formEmail;

    public FormEmailPage(WebDriver driver) {
        super(driver);
    }

    public FormEmailPage inputEmail (String eMailAddress){
        waitUntilElementToBeVisible(eMail);
        eMail.sendKeys(eMailAddress);
        return this;
    }

    public FormEmailPage submitFormEmail (){
        clickButtonWhenClickable(sendEmail);
        return this;
    }
}
