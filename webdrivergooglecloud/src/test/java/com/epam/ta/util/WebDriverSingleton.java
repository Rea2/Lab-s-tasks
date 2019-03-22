package com.epam.ta.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;



import java.net.URL;
import java.util.Arrays;

public class WebDriverSingleton {
    private static WebDriver instance;
    private static final String CHROMEDRIVER_PATH = "src/com.epam.ta.test/resources/chromedriver2.exe";
    private static final String GECKODRIVER_PATH = "src/com.epam.ta.test/resources/geckodriver.exe";

    private WebDriverSingleton() {
    }

    public static WebDriver getWebDriverInstance() {
        if (instance != null) {
            return instance;
        }
        return instance = init();
    }

    public static WebDriver getRemoteDriverInstance(String browserName) throws Exception {
        WebDriver driver = null;
        if (browserName.equalsIgnoreCase("firefox")){
            System.setProperty("webdriver.gecko.com.epam.ta.driver", GECKODRIVER_PATH);
            FirefoxOptions options = new FirefoxOptions();
            System.out.println("firefox");
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
            System.out.println("firefox-2");
        } else if (browserName.equalsIgnoreCase("edge")) {
            EdgeOptions options = new EdgeOptions();
            System.out.println("edge");
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
            System.out.println("edge-2");

        } else if (browserName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.com.epam.ta.driver", CHROMEDRIVER_PATH);
            ChromeOptions options = new ChromeOptions();
            options.setCapability("chrome.switches", Arrays.asList("--homepage=about:blank"));
            System.out.println("chrome");
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
            System.out.println("chrome-2");

        } else throw new Exception();
        return driver ;
    }



    private static WebDriver init() {
//+++++++ RemoteDriver +++++++++++++++++++++++++++++++
//        System.setProperty("webdriver.gecko.com.epam.ta.driver", GECKODRIVER_PATH);
//        FirefoxOptions options = new FirefoxOptions();
//        options.setCapability("chrome.switches", Arrays.asList("--homepage=about:blank"));
//
//        WebDriver com.epam.ta.driver = null;
//        try {
//            com.epam.ta.driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }

//+++++++ FirefoxDriver +++++++++++++++++++++++++++++
//        System.setProperty("webdriver.gecko.com.epam.ta.driver", GECKODRIVER_PATH);
//        FirefoxOptions options = new FirefoxOptions();
//        options.setCapability("chrome.switches", Arrays.asList("--homepage=about:blank"));
//        WebDriver com.epam.ta.driver = new FirefoxDriver(options);
//------------------------------------------

//+++++++  EdgeDriver +++++++++++++++++++++++++++++
//        EdgeOptions options = new EdgeOptions();
//        options.setCapability("chrome.switches", Arrays.asList("--homepage=about:blank"));
//        WebDriver com.epam.ta.driver = new EdgeDriver(options);
//------------------------------------------

//+++++++ChromeDriver
        System.setProperty("webdriver.chrome.com.epam.ta.driver", CHROMEDRIVER_PATH);
        ChromeOptions options = new ChromeOptions();
        options.setCapability("chrome.switches", Arrays.asList("--homepage=about:blank"));
        WebDriver driver = new ChromeDriver(options);
//        com.epam.ta.driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
//        com.epam.ta.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    private static WebDriver initRemoteDriver() {


        System.setProperty("webdriver.chrome.com.epam.ta.driver", CHROMEDRIVER_PATH);
        ChromeOptions options = new ChromeOptions();
        options.setCapability("chrome.switches", Arrays.asList("--homepage=about:blank"));
        WebDriver driver = new ChromeDriver(options);
//        com.epam.ta.driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
//        com.epam.ta.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
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
