package hotelbookings.usecases;

import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static hotelbookings.journey.tasks.checkBookingTask.assertUnsavedBooking;
import static hotelbookings.journey.tasks.checkBookingTask.assertWithoutNamesUnsavedBooking;

public class sadPathTests extends AutomatedTests {

    private final String LAST_NAME = "SAD_PATH";

    @Before
    public void setUpData() {
        randomFirstName = UUID.randomUUID().toString();
    }

    @Test
    public void shouldNotMakeAReservationWithoutDates() {
        givenUserFillsInBookingForm(randomFirstName, LAST_NAME, PRICE, DEPOSIT_PAID);

        whenUserSavesBooking();

        thenBookingIsNotSavedFor(randomFirstName, LAST_NAME);
    }

    @Test
    public void shouldNotMakeAReservationWithoutCheckInDate() {
        String checkInDate = "";

        givenUserMakesBooking(randomFirstName, LAST_NAME, PRICE, DEPOSIT_PAID, checkInDate, CHECK_OUT_DATE);

        whenUserSavesBooking();

        thenBookingIsNotSavedFor(randomFirstName, LAST_NAME);
    }

    @Test
    public void shouldNotMakeAReservationWithoutCheckOutDate() {
        String checkOutDate = "";

        givenUserMakesBooking(randomFirstName, LAST_NAME, PRICE, DEPOSIT_PAID, CHECK_IN_DATE, checkOutDate);

        whenUserSavesBooking();

        thenBookingIsNotSavedFor(randomFirstName, LAST_NAME);
    }

    @Test
    public void shouldNotMakeReservationWithoutNames() {
        randomFirstName = "";
        lastName = "";
        String checkOutDate = "2019-11-10";

        givenUserMakesBooking(randomFirstName, lastName, PRICE, DEPOSIT_PAID, CHECK_IN_DATE, checkOutDate);

        whenUserSavesBooking();

        thenBookingIsNotSavedUsing(PRICE, DEPOSIT_PAID, CHECK_IN_DATE, checkOutDate);
    }

    @Test
    public void shouldNotMakeReservationWithoutLastName() {
        lastName = "";

        givenUserMakesBooking(randomFirstName, lastName, PRICE, DEPOSIT_PAID, CHECK_IN_DATE, CHECK_OUT_DATE);

        whenUserSavesBooking();

        thenBookingIsNotSavedFor(randomFirstName, lastName);
    }

    @Test
    public void shouldNotMakeReservationWithoutFirstName() {
        randomFirstName = "";

        givenUserMakesBooking(randomFirstName, LAST_NAME, PRICE, DEPOSIT_NOT_PAID, CHECK_IN_DATE, CHECK_OUT_DATE);

        whenUserSavesBooking();

        thenBookingIsNotSavedFor(randomFirstName, LAST_NAME);
    }

    @Test
    public void shouldNotMakeReservationWithoutPrice() {
        String price = "";

        givenUserMakesBooking(randomFirstName, LAST_NAME, price, DEPOSIT_PAID, CHECK_IN_DATE, CHECK_OUT_DATE);

        whenUserSavesBooking();

        thenBookingIsNotSavedFor(randomFirstName, LAST_NAME);
    }

    @Test
    public void shouldNotMakeReservationWithInvalidPrice() {
        String price = "#999.99";

        givenUserMakesBooking(randomFirstName, LAST_NAME, price, DEPOSIT_PAID, CHECK_IN_DATE, CHECK_OUT_DATE);

        whenUserSavesBooking();

        thenBookingIsNotSavedFor(randomFirstName, LAST_NAME);
    }

    //Thens
    private void thenBookingIsNotSavedFor(String firstName, String lastName) {
        assertUnsavedBooking(firstName, lastName);
    }

    private void thenBookingIsNotSavedUsing(String price, String deposit, String checkIn, String checkOut) {
        assertWithoutNamesUnsavedBooking(price, deposit, checkIn, checkOut);
    }

}
