package hotelbookings.bugs;

import hotelbookings.journey.tasks.checkBookingTask;
import hotelbookings.journey.tasks.pickBookingDateTask;
import hotelbookings.usecases.AutomatedTests;
import org.junit.Test;

import java.util.UUID;

public class bugTests extends AutomatedTests {

    private static final String DEPOSIT_PAID = "true";
    private static final String INVALID_CHECK_OUT = "1270231";
    private static final String INVALID_CHECK_IN = "1292192";
    private static final String PRICE = "88.99";
    private static final String DEPOSIT_NOT_PAID = "false";
    private static String randomFirstName;
    private String lastName;

    @Test
    public void shouldNotMakeBookingInThePast() {
        randomFirstName = UUID.randomUUID().toString();
        lastName = "BUG_PATH_PREVIOUS_MONTH";
        int PREVIOUS_MONTH = -1;
        int checkInDay = 11;
        int checkOutDay = 13;

        givenUserFillsInBookingForm(randomFirstName, lastName, PRICE, DEPOSIT_NOT_PAID);
        givenUserClicksOnTheCalenderToCheckInForPreviousMonth(checkInDay);
        givenUserClicksOnTheCalenderToCheckOutForCurrentMonth(checkOutDay);

        whenUserSavesBooking();

        thenIncorrectBookingWithPastCheckInDateIsSaved(randomFirstName, lastName, PRICE, DEPOSIT_NOT_PAID,
                nextMonth(checkInDay, PREVIOUS_MONTH), currentMonth(checkOutDay));
    }

    private void thenIncorrectBookingWithPastCheckInDateIsSaved(String firstName, String lastName, String price, String deposit,
                                                                String checkIn, String checkOut) {
        thenBookingIsSavedFor(firstName, lastName, price, deposit, checkIn, checkOut);
    }

    @Test
    public void shouldNotMakeBookingWithInvalidDates() {
        randomFirstName = UUID.randomUUID().toString();
        lastName = "BUG_PATH_INVALID_DATES";

        givenUserMakesBooking(randomFirstName, lastName, PRICE, DEPOSIT_PAID, INVALID_CHECK_IN, INVALID_CHECK_OUT);

        thenIncorrectBookingIsSaved(randomFirstName, lastName);
    }

    //Givens
    private void givenUserClicksOnTheCalenderToCheckInForPreviousMonth(int checkIn) {
        pickBookingDateTask.clickOnCalenderCheckInPreviousMonth(1, checkIn);
    }

    private void givenUserClicksOnTheCalenderToCheckOutForCurrentMonth(int checkOut) {
        pickBookingDateTask.clickOnCalenderCurrentMonthForCheckOut(checkOut);
    }

    //Thens
    private void thenIncorrectBookingIsSaved(String firstName, String lastName) {
        checkBookingTask.assertInvalidSavedBooking(firstName, lastName);
    }

}
