package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PO_FormEmail extends PO {

    @FindBy(xpath = "//input[@id =  'input_369']")
    private WebElement eMail;

    @FindBy(xpath = "//button[@aria-label='Send Email']")
    private WebElement sendEmail;

    public PO_FormEmail(WebDriver driver) {
        super(driver);
    }

    public PO_FormEmail inputEmail (String eMailAddress){
        eMail.sendKeys(eMailAddress);
        return this;
    }

    public PO_FormEmail submitFormEmail (){
        waitUntilElementToBeClickable(sendEmail);
        sendEmail.click();
        return this;
    }







}
