package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PO_Form extends PO {

    private String vmClassExpected = "regular";
    private String instanceTypeExpected = "n1-standard-8";
    private String regionExpected = "Iowa";
    private String localSSDExpected = "2x375 GB";
    private String commitmentTermExpected = "1 Year";

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


    public PO_Form(WebDriver driver) {
        super(driver);
    }

    public String getTextFromVmClass () {
        System.out.println(isWebElementExist(vmClass));
        System.out.println(vmClass.getTagName());
        System.out.println(vmClass.getText());
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

    public String getTextCommitmentTerm () {
        return commitmentTerm.getText();
    }
}











