package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.Arrays;


public class WebDriverSingleton {
    private static WebDriver instance;
    private static final String chromedriver_path = "src/test/resources/chromedriver2.exe";

    private WebDriverSingleton() {
    }

    public static WebDriver getWebDriverInstance() {
        if (instance != null) {
            return instance;
        }
        return instance = init();
    }

    private static WebDriver init() {
        System.setProperty("webdriver.chrome.driver", chromedriver_path);
        ChromeOptions options = new ChromeOptions();
        options.setCapability("chrome.switches", Arrays.asList("--homepage=about:blank"));
        WebDriver driver = new ChromeDriver(options);
//        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
//        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    public static void kill() {
        if (instance != null) {
            try {
                instance.quit();
            } catch (Exception e) {
                System.out.println("Can not kill browser");
            } finally {
                instance = null;
            }
        }
    }
}
