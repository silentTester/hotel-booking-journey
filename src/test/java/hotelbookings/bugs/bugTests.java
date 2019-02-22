package hotelbookings.bugs;

import hotelbookings.usecases.AutomatedTests;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static hotelbookings.journey.tasks.checkBookingTask.assertInvalidSavedBooking;
import static hotelbookings.journey.tasks.pickBookingDatesTask.clickOnCalenderCheckInPreviousMonth;
import static hotelbookings.journey.tasks.pickBookingDatesTask.clickOnCalenderCurrentMonthForCheckOut;

public class bugTests extends AutomatedTests {

    private final String INTEGER_CHECK_OUT_DATE = "1270231";
    private final String INTEGER_CHECK_IN_DATE = "1292192";
    private final String INVALID_CHECK_OUT_DATE = "2019-02-31";

    @Before
    public void setUpData() {
        randomFirstName = UUID.randomUUID().toString();
    }

    @Test
    public void shouldNotMakeBookingInThePast() {
        lastName = "BUG_PATH_PREVIOUS_MONTH";

        givenUserFillsInBookingForm(randomFirstName, lastName, PRICE, DEPOSIT_NOT_PAID);
        givenUserClicksOnTheCalenderToCheckInForPreviousMonth(CHECK_IN_DAY);
        givenUserClicksOnTheCalenderToCheckOutForCurrentMonth(CHECK_OUT_DAY);

        whenUserSavesBooking();

        thenIncorrectBookingSavedWithPastCheckInDate(randomFirstName, lastName, PRICE, DEPOSIT_NOT_PAID,
                currentMonthOf(CHECK_IN_DAY, PREVIOUS_MONTH), currentMonthOf(CHECK_OUT_DAY, CURRENT_MONTH));
    }

    @Test
    public void shouldNotMakeBookingUsingIntegersAsDates() {
        lastName = "BUG_PATH_INTEGER_DATES";

        givenUserMakesBooking(randomFirstName, lastName, PRICE, DEPOSIT_PAID, INTEGER_CHECK_IN_DATE, INTEGER_CHECK_OUT_DATE);

        thenIncorrectBookingSaved(randomFirstName, lastName);
    }

    @Test
    public void shouldNotMakeReservationWithInvalidCheckOutDate() {
        lastName = "BUG_PATH_INVALID_CHECK_OUT_DATE";

        givenUserMakesBooking(randomFirstName, lastName, PRICE, DEPOSIT_PAID, CHECK_IN_DATE, INVALID_CHECK_OUT_DATE);

        whenUserSavesBooking();

        thenIncorrectBookingSaved(randomFirstName, lastName);
    }

    //Givens
    private void givenUserClicksOnTheCalenderToCheckInForPreviousMonth(int checkIn) {
        clickOnCalenderCheckInPreviousMonth(1, checkIn);
    }

    private void givenUserClicksOnTheCalenderToCheckOutForCurrentMonth(int checkOut) {
        clickOnCalenderCurrentMonthForCheckOut(checkOut);
    }

    //Thens
    private void thenIncorrectBookingSaved(String firstName, String lastName) {
        assertInvalidSavedBooking(firstName, lastName);
    }

    private void thenIncorrectBookingSavedWithPastCheckInDate(String firstName, String lastName, String price, String deposit,
                                                              String checkIn, String checkOut) {
        thenBookingIsSavedFor(firstName, lastName, price, deposit, checkIn, checkOut);
    }

}
