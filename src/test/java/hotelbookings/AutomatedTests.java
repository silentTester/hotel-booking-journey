package hotelbookings;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.By.xpath;

public class AutomatedTests {

    private static final String TEST_URL = "http://hotel-test.equalexperts.io";
    private static final String WEB_DRIVER = "webdriver.chrome.driver";
    private static final String WEB_DRIVER_PATH = "/usr/local/bin/chromedriver";
    private static final String[] HEADLESS_MODE = {"--headless", "--disable-gpu", "--window-size=1920,1200", "--ignore-certificate-errors"};
    private static final String[] NON_HEADLESS_MODE = {"--disable-gpu", "--window-size=1920,1200", "--ignore-certificate-errors"};
    private static final int TIME_OUT_IN_SECONDS = 20;

    protected static WebDriver driver;
    protected static WebDriverWait wait;

    @BeforeClass
    public static void init() {
        System.setProperty(WEB_DRIVER, WEB_DRIVER_PATH);
        ChromeOptions options = new ChromeOptions();
        options.addArguments(NON_HEADLESS_MODE);
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS);
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

    //commons
    private static void userNavigatesToURL(String url) {
        driver.navigate().to(url);
    }

    @Before
    public void navigateToHotelPage() {
        givenUserIsOnTheHotelBookingForm();
    }

    //Givens
    protected void givenUserIsOnTheHotelBookingForm() {
        userNavigatesToURL(TEST_URL);

        WebElement text = driver.findElement(xpath("//div[contains(@class, 'jumbotron')]"));
        assertEquals("Hotel booking form", text.getText());
    }

}
