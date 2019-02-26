package hotelbookings.configuration;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverConfig {

    private static final String WEB_DRIVER = "webdriver.chrome.driver";
    private static final String WEB_DRIVER_PATH = "/usr/local/bin/chromedriver";
    private static final int TIME_OUT_SECONDS = 5;
    protected static WebDriver driver;
    protected static WebDriverWait wait;

    @BeforeClass
    public static void init() {
        System.setProperty(WEB_DRIVER, WEB_DRIVER_PATH);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        driver = new ChromeDriver(chromeOptions);
        wait = new WebDriverWait(driver, TIME_OUT_SECONDS);
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

}
