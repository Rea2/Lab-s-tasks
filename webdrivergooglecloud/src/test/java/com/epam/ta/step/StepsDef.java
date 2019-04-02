package com.epam.ta.step;

import com.epam.ta.driver.DriverSingleton;
import com.epam.ta.page.cloud_google.CloudPage;
import com.epam.ta.page.cloud_google.FormEmailPage;
import com.epam.ta.page.cloud_google.FormPage;
import com.epam.ta.page.cloud_google.FramePage;
import com.epam.ta.page.tenminutesmail.TenMinuteEmailPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StepsDef {
    public static final String CURRENCY = "USD";
    // Ожидаемые результаты
    public static final String TOTAL_ESTIMATED_COST_EXPECTED = "1,187.77";
    private static final String ERROR_MESSAGE = "wrong value in the filled form\n";
    private WebDriver driver = null;
    private String estimatedCostFromGoogleWebSite;
    private List<String> tabs = null;
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


    @Given("^I opened Calculator Engine tab$")
    public void openCalculatorEngineForm() {
        driver = initBrowser();
        frame = openCalculateForm();
    }

    @When("^I submitted filled Calculator's Engine with parameters:$")
    public void iSubmittedFilledCalculatorSEngineForm(DataTable dataTable) {
        Map<String, String> inputData = dataTable.asMap(String.class, String.class);
        fillCalculatorForm(inputData).clickAddToEstimate();
    }

    @Then("^Data on the estimate form is the same as was submitted$")
    public void dataOnTheEstimateFormIsTheSameAsWasSubmitted() {
        Assert.assertEquals(getErrorsInCloudForm(), "", getErrorsInCloudForm());
    }

    @Then("^Estimate form contains the expected price$")
    public void estimateFormContainsTheCorrectPrice() {
        Assert.assertEquals(getErrorsInTotalEstimatedCost(), "",
                getErrorsInTotalEstimatedCost());
    }

    @When("^I submit filled Email Estimate Form with specified email address$")
    public void iSubmitFilledEmailEstimateFormWithSpecifiedEmailAddress() {
        form = PageFactory.initElements(driver, FormPage.class);

        //Считвываем строку со стоимостью из результата заполненной формы, это будет expected results
        estimatedCostFromGoogleWebSite = form.getTextFromTotalEstimatedCost();
        formEmail = form.clickEmailEstimate();

        // Создаем  новую вкладку, переключаемся на нее
        ((JavascriptExecutor) driver).executeScript("window.open()");
        tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        String emailAddress = getTemporaryEmailAddress();

        // Возвращаемся на предыщую вкладку со страницей cloud google
        driver.switchTo().window(tabs.get(0));

        // Заходим во фрейм
        driver.switchTo().frame("idIframe");

        // Вводим email в ранее открыую форму и отправляем
        formEmail.inputEmail(emailAddress);
        formEmail.submitFormEmail();
    }

    @Then("^I receive email with  total estimate cost on the specified email address$")
    public void iReceiveEmailWithTotalEstimateCostOnTheSpecifiedEmailAddress() {
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

    private FramePage fillCalculatorForm(Map<String, String> inputValues) {
        frame.inputNumberOfInstances(inputValues.get("Number of instances"))
                .selectOperatingSystem(inputValues.get("Operating System / Software"))
                .selectVmClass(inputValues.get("VM Class"))
                .selectInstantType(inputValues.get("Instance type"));
        if (inputValues.get("Add GPUs") == "true") {
            frame.tickAddGPU()
                    .selectNumberOfGPUs(inputValues.get("Number of GPUs"))
                    .selectGPUsType(inputValues.get("GPU type"));
        }
        return frame
                .selectLocalSsd(inputValues.get("Local SSD"))
                .selectDataCenter(inputValues.get("Data center location"))
                .selectCommittedUsage(inputValues.get("Committed Usage"));
    }

    // Вспомогательные методы
    public WebDriver initBrowser() {
        return DriverSingleton.getDriver();
    }

    public FramePage openCalculateForm() {
        driver = initBrowser();

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
    public String getErrorsInCloudForm() {
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
    public String getErrorsInTotalEstimatedCost() {
        StringBuilder verificationErrors = new StringBuilder();
        if (!form.getTextFromTotalEstimatedCost().contains(CURRENCY)) {
            verificationErrors.append("Wrong type of currency. \"" + CURRENCY + "\" expected.");
        }

        if (!form.getTextFromTotalEstimatedCost().contains(TOTAL_ESTIMATED_COST_EXPECTED)) {
            verificationErrors.append("Wrong value of \" Total Estimated Cost \".\n" +
                    "Actual result: " + form.getTextFromTotalEstimatedCost() + "\n" +
                    "But it is expected: " + CURRENCY + " " + TOTAL_ESTIMATED_COST_EXPECTED);
        }

        return verificationErrors.toString();
    }

    private FramePage submitFilledCalculatorForm() {
        fillCalculatorForm();
        return frame.clickAddToEstimate();
    }

    private FramePage fillCalculatorForm() {
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
