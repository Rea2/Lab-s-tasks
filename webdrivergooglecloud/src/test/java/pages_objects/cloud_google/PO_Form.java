package pages_objects.cloud_google;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages_objects.PO;

public class PO_Form extends PO {

    @FindBy(xpath = "//div[contains(text(), 'VM class')]")
    private WebElement vmClass;

    @FindBy(xpath = "//div[contains(text(), 'Instance type')]")
    private WebElement instanceType;

    @FindBy(xpath = "//div[contains(text(), 'Region')]")
    private WebElement region;

    @FindBy(xpath = "//div[contains(text(), ' local SSD space')]")
    private WebElement localSSD;

    @FindBy(xpath = "//div[contains(text(), 'Commitment term')]")
    private WebElement commitmentTerm;

    @FindBy(xpath = "//h2/b[@class = 'ng-binding']")
    private WebElement totalEstimatedCost;

    @FindBy(xpath = "//button[@aria-label = 'Email Estimate']")
    private WebElement emailEstimate;

    public PO_Form(WebDriver driver) {
        super(driver);
    }

    public String getTextFromVmClass () {
        return vmClass.getText();
    }

    public String getTextFromInstanceType () {
        return instanceType.getText();
    }

    public String getTextFromRegion () {
        return region.getText();
    }

    public String getTextFromLocalSSD () {
        return localSSD.getText();
    }

    public String getTextFromTotalEstimatedCost() {
        return totalEstimatedCost.getText();
    }

    public String getTextFromCommittedTerm() {
        return commitmentTerm.getText();
    }

    public PO_FormEmail clickEmailEstimate() {
        clickButtonWhenClickable(emailEstimate);
        return PageFactory.initElements(driver, PO_FormEmail.class) ;
    }
}











