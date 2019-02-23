package hotelbookings.bugs;

import hotelbookings.usecases.AutomatedTests;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static hotelbookings.journey.tasks.checkBookingTask.assertInvalidSavedBooking;

public class bugTests extends AutomatedTests {

    private final String PAST_CHECK_IN_DATE = "2018-04-07";
    private final String PAST_CHECK_OUT_DATE = "2018-04-09";
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

        givenUserMakesBooking(randomFirstName, lastName, PRICE, DEPOSIT_NOT_PAID, PAST_CHECK_IN_DATE,
                PAST_CHECK_OUT_DATE);

        whenUserSavesBooking();

        thenIncorrectBookingSavedWithPastDates(randomFirstName, lastName, PRICE, DEPOSIT_NOT_PAID, PAST_CHECK_IN_DATE,
                PAST_CHECK_OUT_DATE);
    }

    @Test
    public void shouldNotMakeBookingUsingIntegersAsDates() {
        lastName = "BUG_PATH_INTEGER_DATES";

        givenUserMakesBooking(randomFirstName, lastName, PRICE, DEPOSIT_PAID, INTEGER_CHECK_IN_DATE,
                INTEGER_CHECK_OUT_DATE);

        thenIncorrectBookingSaved(randomFirstName, lastName);
    }

    @Test
    public void shouldNotMakeReservationWithInvalidCheckOutDate() {
        lastName = "BUG_PATH_INVALID_CHECK_OUT_DATE";

        givenUserMakesBooking(randomFirstName, lastName, PRICE, DEPOSIT_PAID, CHECK_IN_DATE, INVALID_CHECK_OUT_DATE);

        whenUserSavesBooking();

        thenIncorrectBookingSaved(randomFirstName, lastName);
    }

    //Thens
    private void thenIncorrectBookingSaved(String firstName, String lastName) {
        assertInvalidSavedBooking(firstName, lastName);
    }

    private void thenIncorrectBookingSavedWithPastDates(String firstName, String lastName, String price, String deposit,
                                                        String checkIn, String checkOut) {
        thenBookingIsSavedFor(firstName, lastName, price, deposit, checkIn, checkOut);
    }

}
