package hotelbookings.usecases;

import hotelbookings.AutomatedTests;
import hotelbookings.journey.tasks.cancelBookingTask;
import hotelbookings.journey.tasks.checkBookingTask;
import hotelbookings.journey.tasks.makeBookingTask;
import org.junit.Test;

import java.util.UUID;


public class happyPathTests extends AutomatedTests {

    private static final String LAST_NAME = "HAPPY_PATH";
    private static final String PRICE = "100.99";
    private static final String DEPOSIT = "true";
    private static final String CHECK_IN = "2019-03-01";
    private static final String CHECK_OUT = "2019-03-03";
    private static String randomFirstName;
    private static String lastName;


    @Test
    public void shouldMakeABooking() {
        randomFirstName = UUID.randomUUID().toString();

        givenUserMakesBooking(randomFirstName, LAST_NAME, PRICE, DEPOSIT, CHECK_IN, CHECK_OUT);

        thenBookingIsSavedFor(randomFirstName, LAST_NAME, PRICE, DEPOSIT, CHECK_IN, CHECK_OUT);
    }

    @Test
    public void shouldDeleteABooking() {
        randomFirstName = UUID.randomUUID().toString();
        lastName = "DELETE_HAPPY_PATH";

        givenABookingExistsFor(randomFirstName, lastName, PRICE, DEPOSIT, CHECK_IN, CHECK_OUT);

        whenUserDeletesBooking(randomFirstName, lastName, PRICE, DEPOSIT, CHECK_IN, CHECK_OUT);

        thenBookingIsDeletedFor(randomFirstName, lastName, PRICE, DEPOSIT, CHECK_IN, CHECK_OUT);
    }

    //Givens
    private void givenUserMakesBooking(String firstName, String lastName, String price, String deposit,
                                       String checkIn, String checkOut) {
        givenUserIsOnTheHotelBookingForm();

        makeBookingTask.saveBookingFor(firstName, lastName, price, deposit, checkIn, checkOut);
    }

    private void givenABookingExistsFor(String firstName, String lastName, String price, String deposit,
                                        String checkIn, String checkOut) {
        givenUserMakesBooking(firstName, lastName, price, deposit, checkIn, checkOut);

        thenBookingIsSavedFor(firstName, lastName, price, deposit, checkIn, checkOut);
    }

    //Whens
    private void whenUserDeletesBooking(String firstName, String lastName, String price, String deposit,
                                        String checkIn, String checkOut) {
        cancelBookingTask.deleteBookingFor(firstName, lastName, price, deposit, checkIn, checkOut);
    }

    //Thens
    private void thenBookingIsSavedFor(String firstName, String lastName, String price, String deposit,
                                       String checkIn, String checkOut) {
        checkBookingTask.assertBookingIsSaved(firstName, lastName, price, deposit, checkIn, checkOut);
    }

    private void thenBookingIsDeletedFor(String firstName, String lastName, String price, String deposit,
                                         String checkIn, String checkOut) {
        checkBookingTask.assertBookingIsDeleted(firstName, lastName, price, deposit, checkIn, checkOut);
    }

}
