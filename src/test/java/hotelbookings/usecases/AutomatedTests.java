package hotelbookings.usecases;

import hotelbookings.journey.tasks.makeBookingTask;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static hotelbookings.journey.tasks.checkBookingTask.assertSavedBooking;
import static hotelbookings.journey.tasks.makeBookingTask.fillsInFormWithoutDates;
import static hotelbookings.journey.tasks.makeBookingTask.saveBookingFor;
import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.By.xpath;

public class AutomatedTests {

    protected String randomFirstName;
    protected String lastName;

    protected final String PRICE = "100.99";
    protected final String DEPOSIT_PAID = "true";
    protected final String DEPOSIT_NOT_PAID = "false";
    protected final String CHECK_IN_DATE = "2019-04-07";
    protected final String CHECK_OUT_DATE = "2019-04-09";
    protected final int NUMBER_OF_DUPLICATES = 2;
    protected final int CHECK_IN_DAY = 21;
    protected final int CHECK_OUT_DAY = 23;
    protected final int PREVIOUS_MONTH = -1;
    protected final int CURRENT_MONTH = 0;

    private static final String TEST_URL = "http://hotel-test.equalexperts.io";
    private static final String WEB_DRIVER = "webdriver.chrome.driver";
    private static final String WEB_DRIVER_PATH = "/usr/local/bin/chromedriver";
    protected static final int TIME_OUT_SECONDS = 10;

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

    protected void givenUserMakesBooking(String firstName, String lastName, String price, String deposit,
                                         String checkIn, String checkOut) {
        givenUserIsOnTheHotelBookingForm();

        saveBookingFor(firstName, lastName, price, deposit, checkIn, checkOut);
    }

    protected void givenUserFillsInBookingForm(String firstName, String lastName, String price, String depositPaid) {
        fillsInFormWithoutDates(firstName, lastName, price, depositPaid);
    }

    //Whens
    protected void whenUserSavesBooking() {
        makeBookingTask.clickSave();
    }

    //Thens
    protected void thenBookingIsSavedFor(String firstName, String lastName, String price, String deposit,
                                         String checkIn, String checkOut) {
        assertSavedBooking(firstName, lastName, price, deposit, checkIn, checkOut);
    }

    //commons
    private static void userNavigatesToURL(String url) {
        driver.navigate().to(url);
    }

    protected String currentMonthOf(int day, int month) {
        LocalDate currentTime = LocalDate.now().plusMonths(month);
        return currentTime.format(DateTimeFormatter.ofPattern("YYYY-MM-" + String.format("%02d", day)));
    }

}
