package com.epam.ta.test;

import com.epam.ta.driver.DriverSingleton;
import com.epam.ta.page.cloud_google.CloudPage;
import com.epam.ta.page.cloud_google.FormEmailPage;
import com.epam.ta.page.cloud_google.FormPage;
import com.epam.ta.page.cloud_google.FramePage;
import com.epam.ta.page.tenminutesmail.TenMinuteEmailPage;
import com.epam.ta.util.TestListener;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;

@Listeners({TestListener.class})
public class TestClass {

    public static final String CURRENCY = "USD";  ;
    private static final String ERROR_MESSAGE = "wrong value in the filled form\n";

    private WebDriver driver = null;

    // Ожидаемые результаты
    public static final String TOTAL_ESTIMATED_COST_EXPECTED = "1,187.77";
    private String vmClassExpected = "regular";
    private String instanceTypeExpected = "n1-standard-8";
    private String regionExpected = " Frankfurt";
    private String localSSDExpected = "2x375 GB";
    private String commitmentTermExpected = "1 Year";

    // Экземпляры Page Objects
    private CloudPage page = null;
    private FramePage frame = null;
    private FormPage form = null;
    private FormEmailPage formEmail = null;
    private TenMinuteEmailPage page10MinuteEmail = null;

    @BeforeClass
    public void beforeClass() {
        driver = initBrowser();
        frame = openCalculateForm();

        //Заполняем и отправляем форму с данными для расчета стоимости.
        submitFilledCalculatorForm();
    }

    @AfterClass
    public void afterClass() {
        DriverSingleton.closeDriver();
    }

    @BeforeMethod
    public void beforeTest() {
    }

    @AfterMethod
    public void afterTest() {
    }

    // Проверяем соответствие данных следующих полей: VM Class, Instance type, Region, local SSD, commitment term
    @Test (priority = 1)
    public void testIsFilledFormCorrect()  {
        Assert.assertEquals(getErrorsInCloudForm(), "", getErrorsInCloudForm());
    }

    //  Проверяем что сумма аренды в месяц совпадает с суммой получаемой при ручном прохождении теста.
    @Test (priority = 2)
    public void testTotalEstimatedCost()  {
        Assert.assertEquals(getErrorsInTotalEstimatedCost(), "",
                getErrorsInTotalEstimatedCost());
    }

    //  Дождаться письма с рассчетом стоимости и проверить, что Total Estimated Monthly Cost в письме
    // совпадает с тем, что отображается в калькуляторе
    @Test (priority = 3, enabled = false)
    public void testGettingCalculationsOnEmail() {
        form = PageFactory.initElements(driver, FormPage.class);

        //Считвываем строку со стоимостью из результата заполненной формы, это будет expected results
        String estimatedCostFromGoogleWebSite = form.getTextFromTotalEstimatedCost();
        formEmail  = form.clickEmailEstimate();

        // Создаем  новую вкладку, переключаемся на нее
        ((JavascriptExecutor)driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        String emailAddress =  getTemporaryEmailAddress();

        // Возвращаемся на предыщую вкладку со страницей cloud google
        driver.switchTo().window(tabs.get(0));

        // Заходим во фрейм
        driver.switchTo().frame("idIframe");

        // Вводим email в ранее открыую форму и отправляем
        formEmail.inputEmail(emailAddress);
        formEmail.submitFormEmail();
        driver.switchTo().window(tabs.get(1));
        page10MinuteEmail.openEmailFromGoogleCloud();
        String valueFromEmail = " " + page10MinuteEmail.getTextCostValueFromEmail() + " ";
        Assert.assertTrue(estimatedCostFromGoogleWebSite.contains(valueFromEmail),
                "WebSite: " + estimatedCostFromGoogleWebSite + "\n" +
                   "Email: " + valueFromEmail);

        // Закрываем вкладку с https://10minutemail.comб вовзращаемся на страницу
        // https://cloud.google.com/products/calculator/# и выполняем переход во фрейм
        driver.close();
        driver.switchTo().window(tabs.get(0));
        driver.switchTo().frame("idIframe");
    }


    // Вспомогательные методы
    public WebDriver  initBrowser(){
        return DriverSingleton.getDriver();
    }

    public FramePage openCalculateForm() {

        // Выполняем навгацию по страницам Cloud Google к форме с калькулятором стоимости и ждем появления фрейма
        page = PageFactory.initElements(driver, CloudPage.class);
        page.open()
                .clickExploreAllProducts()
                .clickSeePricing()
                .clickCalculators()
                .waitUntilFrameVisible();

        // Переходим во фрейм с формой для заполнения ввода данных о требуемой конфигурации
        driver.switchTo().frame("idIframe");
        frame = PageFactory.initElements(driver, FramePage.class);
        return frame.clickComputeEngine();
    }

    // Проверяем соответствие данных следующих полей: VM Class, Instance type, Region, local SSD, commitment term
    public String getErrorsInCloudForm()  {
        form = PageFactory.initElements(driver, FormPage.class);
        StringBuilder verificationErrors = new StringBuilder();

        // Проверяем  каждого поле в результирующей форме сегнерированной Google Cloud.
        // В случае отличия фаткического от ожидаемого результат добавляем  информацию об ошибке
        // в итоговое сообщение
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
        return verificationErrors.toString();
    }

    //  Проверка что сумма аренды в месяц совпадает с суммой получаемой при ручном прохождении теста.
    public String getErrorsInTotalEstimatedCost()  {
        StringBuilder verificationErrors = new StringBuilder();
        if (!form.getTextFromTotalEstimatedCost().contains(CURRENCY)) {
            verificationErrors.append("Wrong type of currency. \"" + CURRENCY + "\" expected." );
        }

        if (!form.getTextFromTotalEstimatedCost().contains(TOTAL_ESTIMATED_COST_EXPECTED)) {
            verificationErrors.append("Wrong value of \" Total Estimated Cost \".\n" +
                    "Actual result: " +  form.getTextFromTotalEstimatedCost() +  "\n" +
                    "But it is expected: " + CURRENCY + " " + TOTAL_ESTIMATED_COST_EXPECTED );
        }

        return verificationErrors.toString();
    }

    private FramePage submitFilledCalculatorForm() {
        fillCalculatorForm();
        return frame.clickAddToEstimate();
    }

    private FramePage fillCalculatorForm()  {
        return frame.inputNumberOfInstances("4")
                .selectOperatingSystem("Free: Debian, CentOS, CoreOS, Ubuntu, or other User Provided OS")
                .selectVmClass("Regular")
                .selectInstantType("n1-standard-8    (vCPUs: 8, RAM: 30 GB)")
                .tickAddGPU()
                .selectNumberOfGPUs("1")
                .selectGPUsType("NVIDIA Tesla V100")
                .selectLocalSsd("2x375 GB")
                .selectDataCenter("Frankfurt (europe-west3)")
                .selectCommittedUsage("1 Year");
    }

    private String getTemporaryEmailAddress() {
        // Открываем страницу почтового сервиса "https://10minutemail.com",
        // который сразу же создает нам рандомный почтовый ящик.
        driver.get("https://10minutemail.com");
        page10MinuteEmail = PageFactory.initElements(driver, TenMinuteEmailPage.class);

        // Возвращаем  адрес email
        return page10MinuteEmail.getTextFromInputEmailAddress();
    }
}
