package hotelbookings.bugs;

import hotelbookings.journey.tasks.pickBookingDateTask;
import hotelbookings.usecases.AutomatedTests;
import org.junit.Test;

import java.util.UUID;

public class bugsTests extends AutomatedTests {

    private static final String LAST_NAME = "BUGS_PATH";
    private static final String PRICE = "88.99";
    private static final String DEPOSIT_NOT_PAID = "false";
    private static String randomFirstName;

    @Test
    public void shouldMakeBookingUsingCalendarForPreviousMonth() {
        randomFirstName = UUID.randomUUID().toString();
        int PREVIOUS_MONTH = -1;
        int checkInDay = 11;
        int checkOutDay = 13;

        givenUserFillsInBookingForm(randomFirstName, LAST_NAME, PRICE, DEPOSIT_NOT_PAID);
        givenUserClicksOnTheCalenderToCheckInForPreviousMonth(checkInDay);
        givenUserClicksOnTheCalenderToCheckOutForPreviousMonth(checkOutDay);

        whenUserSavesBooking();

        thenBookingIsSavedFor(randomFirstName, LAST_NAME, PRICE, DEPOSIT_NOT_PAID,
                nextMonth(checkInDay, PREVIOUS_MONTH), currentMonth(checkOutDay));
    }

    //Givens
    private void givenUserClicksOnTheCalenderToCheckInForPreviousMonth(int checkIn) {
        pickBookingDateTask.clickOnCalenderCheckInPreviousMonth(1, checkIn);
    }

    private void givenUserClicksOnTheCalenderToCheckOutForPreviousMonth(int checkOut) {
        pickBookingDateTask.clickOnCalenderCurrentMonthForCheckOut(checkOut);
    }

}
