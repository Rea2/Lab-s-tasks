import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;

import java.util.ArrayList;
import java.util.Arrays;


public class TestClass {
    private StringBuilder verificationErrors = new StringBuilder("");
    private WebDriver driver = null;

    public static final String URL_BASE = "https://cloud.google.com/";
    public static final String ERROR_MESSAGE = "wrong value in the filled form\n";
    public static final String CURRENCY = "USD";
    public static final String TOTAL_ESTIMATED_COST_EXPECTED = "1,187.77";

    private String vmClassExpected = "regular";
    private String instanceTypeExpected = "n1-standard-8";
    private String regionExpected = " Frankfurt";
    private String localSSDExpected = "2x375 GB";
    private String commitmentTermExpected = "1 Year";
    String chromedriver_path = "d:\\Soft\\Webdriver\\chromedriver.exe";
    private PO_Frame frame = null;
    private PO_Form form = null;
    private PO_FormEmail formEmail = null;
    private PO_10minuteEmail page10MinuteEmail = null;

    @BeforeClass
    public void beforeClass() {

        // Создаем экземляр chromedriver
        System.setProperty("webdriver.chrome.driver", chromedriver_path);
        ChromeOptions options = new ChromeOptions();
        options.setCapability("chrome.switches", Arrays.asList("--homepage=about:blank"));
        driver = new ChromeDriver(options);

        driver.manage().window().maximize();

        // Выполняем preconditions, п.п. 1-5 задания
        driver.get(URL_BASE);
        PO_GoogleCloud page = PageFactory.initElements(driver, PO_GoogleCloud.class);
        page.clickExploreAllProducts();
        page.clickSeePricing();
        page.clickCalculators();

        // Переходим во фрейм с формой для заполнения ввода данных о требуемой конфигурации
        driver.switchTo().frame("idIframe");
        frame = page.clickComputeEngine();

        //Заполняем форму данными и нажимаем кнопку "ADD TO ESTIMATE"
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
    }

    @AfterClass
    public void afterClass() {
    //    driver.quit();
    }

    @BeforeMethod
    public void beforeTest() {
        verificationErrors = new StringBuilder();
    }

    @AfterMethod
    public void afterTest() {
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            Assert.fail(verificationErrorString);
        }
    }


    // Проверяем соответствие данных следующих полей: VM Class, Instance type, Region, local SSD, commitment term
    @Test(priority = 1)
    public void testIsFilledFormCorrect()  {
        form = PageFactory.initElements(driver, PO_Form.class);

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

        if (!form.getTextFromCommittedTerm().contains(commitmentTermExpected)) {
            verificationErrors.append("Commitment term: " + ERROR_MESSAGE);
        }
    }

    //Проверяем что сумма аренды в месяц совпадает с суммой получаемой при ручном прохождении теста.
    @Test(priority = 2)
    public void testTotalEstimatedCost()  {
        if (!form.getTextFromTotalEstimatedCost().contains(CURRENCY)) {
            verificationErrors.append("Wrong type of currency. \"" + CURRENCY + "\" expected." );
        }

        if (!form.getTextFromTotalEstimatedCost().contains(TOTAL_ESTIMATED_COST_EXPECTED)) {
            verificationErrors.append("Wrong value of \" Total Estimated Cost \".\n" +
                    "Actual result: " +  form.getTextFromTotalEstimatedCost() +  "\n" +
                    "But it is expected: " + CURRENCY + " " + TOTAL_ESTIMATED_COST_EXPECTED );
        }
    }

    //  Дождаться письма с рассчетом стоимости и проверить что Total Estimated Monthly Cost в письме
    // совпадает с тем, что отображается в калькуляторе
    @Test(priority = 3)
    public void testGettingCalculationsOnEmail() {
        String estimatedCostFromGoogleWebSite = form.getTextFromTotalEstimatedCost();
        formEmail  = form.clickEmailEstimate();

        // Переключаемся на новую вкладку и открываем "https://10minutemail.com"
        ((JavascriptExecutor)driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.get("https://10minutemail.com");
        page10MinuteEmail = PageFactory.initElements(driver, PO_10minuteEmail.class);

        // Получаем адрес нового рандомного 10-ти минтуного ящика
        String eMail = page10MinuteEmail.getTextFromInputEmailAddress();


        // Переключаемся на первую вкладку
        driver.switchTo().window(tabs.get(0));

        // Входим во фрейм
        driver.switchTo().frame("idIframe");
        formEmail.inputEmail(eMail);
        formEmail.submitFormEmail();
        driver.switchTo().window(tabs.get(1));
        page10MinuteEmail.openEmailFromGoogleCloud();
        String valueFromEmail = " " + page10MinuteEmail.getTextCostValueFromEmail() + " ";
        Assert.assertTrue(estimatedCostFromGoogleWebSite.contains(valueFromEmail),
                "WebSite: " + estimatedCostFromGoogleWebSite + "\n" +
                   "Email: " + valueFromEmail);
    }

}





