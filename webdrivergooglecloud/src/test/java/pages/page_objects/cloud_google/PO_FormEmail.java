package pages.page_objects.cloud_google;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.page_objects.PO;

public class PO_FormEmail extends PO {

    @FindBy(xpath = "//input[@id='input_380']")
    private WebElement eMail;

    @FindBy(xpath = "//button[@aria-label='Send Email']")
    private WebElement sendEmail;

    @FindBy(xpath = "//form[@name =  'emailForm']")
    private WebElement formEmail;

    public PO_FormEmail(WebDriver driver) {
        super(driver);
    }

    public PO_FormEmail inputEmail (String eMailAddress){
        waitUntilElementToBeVisible(eMail);
        eMail.sendKeys(eMailAddress);
        return this;
    }

    public PO_FormEmail submitFormEmail (){
        clickButtonWhenClickable(sendEmail);
        return this;
    }
}
