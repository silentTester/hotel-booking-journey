package hotelbookings.usecases;

import hotelbookings.configuration.WebDriverConfig;
import hotelbookings.journey.screen.HotelPage;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static hotelbookings.journey.actions.ReadingAction.getTextFrom;
import static hotelbookings.journey.tasks.checkBookingTask.assertSavedBooking;
import static hotelbookings.journey.tasks.makeBookingTask.*;
import static org.junit.Assert.assertEquals;

public class AutomatedTests {

    protected String randomFirstName;
    private static final int TIME_OUT_SECONDS = 5;
    protected final String PRICE = "100.99";
    protected final String DEPOSIT_PAID = "true";
    protected final String DEPOSIT_NOT_PAID = "false";
    protected final String CHECK_IN_DATE = "2019-04-07";
    protected final String CHECK_OUT_DATE = "2019-04-09";
    private static final String TEST_URL = "http://hotel-test.equalexperts.io";
    private static WebDriver driver = new WebDriverConfig().getWebDriver();
    protected static WebDriverWait wait = new WebDriverWait(driver, TIME_OUT_SECONDS);
    private final String HOTEL_BOOKING_FORM_HEADER = "Hotel booking form";
    protected String lastName;

    @Before
    public void navigateToHotelPage() {
        givenUserIsOnTheHotelBookingForm();
    }

    //Givens
    protected void givenUserIsOnTheHotelBookingForm() {
        userNavigatesToURL(TEST_URL);

        assertEquals(HOTEL_BOOKING_FORM_HEADER, getTextFrom(HotelPage.headerPage));
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
        clickSave();
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

    protected String formatDate(int day, int month) {
        LocalDate localDate = LocalDate.now().plusMonths(month);
        return localDate.format(DateTimeFormatter.ofPattern("YYYY-MM-" + String.format("%02d", day)));
    }

    protected String formatDateForPast(int day, int month) {
        LocalDate localDate = LocalDate.now().minusMonths(month);
        return localDate.format(DateTimeFormatter.ofPattern("YYYY-MM-" + String.format("%02d", day)));
    }

}
