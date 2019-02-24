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
        lastName = "BUG_PATH_BOOKING_PAST";

        givenUserMakesBooking(randomFirstName, lastName, PRICE, DEPOSIT_NOT_PAID, PAST_CHECK_IN_DATE,
                PAST_CHECK_OUT_DATE);

        whenUserSavesBooking();

        thenIncorrectBookingSavedWithPastDates(randomFirstName, lastName, PRICE, DEPOSIT_NOT_PAID, PAST_CHECK_IN_DATE,
                PAST_CHECK_OUT_DATE);
    }

    @Test
    public void shouldNotMakeBookingUsingIntegersAsDates() {
        lastName = "BUG_PATH_BOOKING_INTEGERS";

        givenUserMakesBooking(randomFirstName, lastName, PRICE, DEPOSIT_PAID, INTEGER_CHECK_IN_DATE,
                INTEGER_CHECK_OUT_DATE);

        thenIncorrectBookingSaved(randomFirstName, lastName);
    }

    @Test
    public void shouldNotMakeBookingWithInvalidCheckOutDate() {
        lastName = "BUG_PATH_INVALID_CHECK_OUT_DATE";

        givenUserMakesBooking(randomFirstName, lastName, PRICE, DEPOSIT_PAID, CHECK_IN_DATE, INVALID_CHECK_OUT_DATE);

        whenUserSavesBooking();

        thenIncorrectBookingSaved(randomFirstName, lastName);
    }

    @Test
    public void shouldNotMakeBookingWithAstronomicalValues() {
        randomFirstName = "dshfsdafhajkdf3DSHFJASDHFKJ1213%!*###";
        lastName = "BUG_PATH_ASTRONOMICAL_VALUES";
        String price = "2342134223342";

        givenUserMakesBooking(randomFirstName, lastName, price, DEPOSIT_PAID, CHECK_IN_DATE, INVALID_CHECK_OUT_DATE);

        whenUserSavesBooking();

        thenIncorrectBookingSaved(randomFirstName, lastName);
    }

    @Test
    public void shouldNotMakeBookingWithNegativePrice() {
        lastName = "BUG_PATH_NEGATIVE_PRICE";
        String price = "-129.99";

        givenUserMakesBooking(randomFirstName, lastName, price, DEPOSIT_PAID, CHECK_IN_DATE, CHECK_OUT_DATE);

        whenUserSavesBooking();

        thenIncorrectBookingSaved(randomFirstName, lastName);
    }

    @Test
    public void shouldNotMakeBookingWithCheckInAfterCheckOut() {
        lastName = "BUG_PATH_CHECK_IN_AFTER_OUT";
        String checkIn = "2019-06-20";
        String checkOut = "2019-06-10";

        givenUserMakesBooking(randomFirstName, lastName, PRICE, DEPOSIT_PAID, checkIn, checkOut);

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
