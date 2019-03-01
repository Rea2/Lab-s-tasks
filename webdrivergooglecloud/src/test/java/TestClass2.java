import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;

import java.util.Arrays;

public class TestClass2 {

    public static final String URL_BASE = "https://cloud.google.com/";
    StringBuilder verificationErrors = new StringBuilder("");
    WebDriver driver = null;

    public static final String ERROR_MESSAGE = "wrong value in the filled form\n";
    private String vmClassExpected = "regular";
    private String instanceTypeExpected = "n1-standard-8";
    private String regionExpected = " Frankfurt";
    private String localSSDExpected = "2x375 GB";
    private String commitmentTermExpected = "1 Year";

    String chromedriver_path = "d:\\Soft\\Webdriver\\chromedriver.exe";


    @BeforeClass
    public void beforeClass() {
        // Создаем экземляр chromedriver
        System.setProperty("webdriver.chrome.driver", chromedriver_path);
        ChromeOptions options = new ChromeOptions();
        options.setCapability("chrome.switches", Arrays.asList("--homepage=about:blank"));
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @AfterClass
    public void afterClass() {
//        driver.quit();
    }

//    @BeforeMethod
//    public void beforeTest() {
//        verificationErrors = new StringBuffer();
//    }
//
//    @AfterMethod
//    public void afterTest() {
//        String verificationErrorString = verificationErrors.toString();
//        if (!"".equals(verificationErrorString)) {
//            Assert.fail(verificationErrorString);
//        }
//    }

    @Test(priority = 1)
    public void testButtonExploreAllProducts() throws InterruptedException {
        driver.get(URL_BASE);
        PO_GoogleCloud page = PageFactory.initElements(driver, PO_GoogleCloud.class);
        page.clickExploreAllProducts();
        page.clickSeePricing();
        page.clickCalculators();

        driver.switchTo().frame("idIframe");

        PO_Frame frame = page.clickComputeEngine();
//        Assert.assertTrue(frame.isFormPresentForReal(), "Form \"ComputeEngine\"  didn't find ");
        frame.inputNumberOfInstances("4")
                .selectOperatingSystem()
                .selectVmClass()
                .selectInstantType()
                .tickAddGPU()
                .selectNumberOfGPUs()
                .selectGPUsType()
                .selectLocalSsd()
                .selectDataCenter()
                .selectCommittedUsage()
                .clickAddToEstimate();


        PO_Form form = PageFactory.initElements(driver, PO_Form.class);

        if (!form.getTextFromVmClass().contains(vmClassExpected)) {
            verificationErrors.append("VMClass: " + ERROR_MESSAGE);
        }

        if (!form.getTextFromInstanceType().contains(instanceTypeExpected)) {
            verificationErrors.append("Instance type: " + ERROR_MESSAGE);
        }

        if (!form.getTextFromRegion().contains(regionExpected)) {
            verificationErrors.append("Region: " + ERROR_MESSAGE);
        }

        if (!form.getTextFromLocalSSD().contains(localSSDExpected)) {
            verificationErrors.append("Total available local SSD space: " + ERROR_MESSAGE);
        }

        if (!form.getTextFromInstanceType().contains(instanceTypeExpected)) {
            verificationErrors.append("Commitment term: " + ERROR_MESSAGE);
        }

        Assert.assertEquals(verificationErrors.toString(), "", verificationErrors.toString());
    }








}





