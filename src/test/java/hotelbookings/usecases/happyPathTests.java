package hotelbookings.usecases;

import hotelbookings.journey.tasks.cancelBookingTask;
import hotelbookings.journey.tasks.checkBookingTask;
import hotelbookings.journey.tasks.makeBookingTask;
import hotelbookings.journey.tasks.pickBookingDateTask;
import org.junit.Test;

import java.util.UUID;


public class happyPathTests extends AutomatedTests {

    private static final String LAST_NAME = "HAPPY_PATH";
    private static final String PRICE = "100.99";
    private static final String DEPOSIT_PAID = "true";
    private static final String DEPOSIT_NOT_PAID = "false";
    private static final String CHECK_IN = "2019-04-07";
    private static final String CHECK_OUT = "2019-04-09";
    private static final int NUMBER_OF_DUPLICATES = 2;
    private static String randomFirstName;
    private static String lastName;

    @Test
    public void shouldMakeABooking() {
        randomFirstName = UUID.randomUUID().toString();

        givenUserMakesBooking(randomFirstName, LAST_NAME, PRICE, DEPOSIT_PAID, CHECK_IN, CHECK_OUT);

        thenBookingIsSavedFor(randomFirstName, LAST_NAME, PRICE, DEPOSIT_PAID, CHECK_IN, CHECK_OUT);
    }

    @Test
    public void shouldCancelABooking() {
        randomFirstName = UUID.randomUUID().toString();
        lastName = "DELETE_HAPPY_PATH";

        givenABookingExistsFor(randomFirstName, lastName, PRICE, DEPOSIT_PAID, CHECK_IN, CHECK_OUT);

        whenUserDeletesBooking(randomFirstName, lastName, PRICE, DEPOSIT_PAID, CHECK_IN, CHECK_OUT);

        thenBookingIsDeletedFor(randomFirstName, lastName, PRICE, DEPOSIT_PAID, CHECK_IN, CHECK_OUT);
    }

    @Test
    public void shouldMakeDuplicateBookings() {
        randomFirstName = UUID.randomUUID().toString();
        lastName = "DUPLICATE_HAPPY_PATH";
        String price = "150";
        String checkIn = "2019-05-01";
        String checkOut = "2019-05-11";

        givenDuplicateReservationsFor(randomFirstName, lastName, price, DEPOSIT_NOT_PAID, checkIn, checkOut);

        thenDuplicateBookingsExistFor(randomFirstName, lastName, price, DEPOSIT_NOT_PAID, checkIn, checkOut);
    }

    @Test
    public void shouldMakeMultipleUniqueBookings() {
        randomFirstName = UUID.randomUUID().toString();
        lastName = "UNIQUE_HAPPY_PATH";

        givenMultipleUniqueReservationsFor(randomFirstName, lastName);

        thenThereAreMultipleReservationsBookedFor(randomFirstName, lastName);
    }

    @Test
    public void shouldMakeBookingUsingCalendar() {
        randomFirstName = UUID.randomUUID().toString();
        lastName = "CALENDAR_HAPPY_PATH";
        String price = "199.5";
        int checkInDay = 21;
        int checkOutDay = 23;

        givenUserFillsInBookingForm(randomFirstName, lastName, price, DEPOSIT_PAID);
        givenUserClicksOnTheCalenderFor(checkInDay, checkOutDay);

        whenUserSavesBooking();

        thenBookingIsSavedFor(randomFirstName, lastName, price, DEPOSIT_PAID,
                currentMonth(checkInDay), currentMonth(checkOutDay));
    }

    @Test
    public void shouldMakeBookingUsingCalendarForNextMonth() {
        randomFirstName = UUID.randomUUID().toString();
        lastName = "CALENDAR_HAPPY_PATH";
        String price = "99.99";
        int checkInDay = 11;
        int checkOutDay = 13;

        givenUserFillsInBookingForm(randomFirstName, lastName, price, DEPOSIT_NOT_PAID);
        givenUserClicksOnTheCalenderToCheckInForNextMonth(checkInDay);
        givenUserClicksOnTheCalenderToCheckOutForNextMonth(checkOutDay);

        whenUserSavesBooking();

        thenBookingIsSavedFor(randomFirstName, lastName, price, DEPOSIT_NOT_PAID,
                nextMonth(checkInDay, 1), nextMonth(checkOutDay, 1));
    }

    //Givens
    private void givenABookingExistsFor(String firstName, String lastName, String price, String deposit,
                                        String checkIn, String checkOut) {
        givenUserMakesBooking(firstName, lastName, price, deposit, checkIn, checkOut);

        thenBookingIsSavedFor(firstName, lastName, price, deposit, checkIn, checkOut);
    }

    private void givenDuplicateReservationsFor(String firstName, String lastName, String price, String deposit,
                                               String checkIn, String checkOut) {
        givenABookingExistsFor(firstName, lastName, price, deposit, checkIn, checkOut);

        givenABookingExistsFor(firstName, lastName, price, deposit, checkIn, checkOut);
    }

    private void givenMultipleUniqueReservationsFor(String firstName, String lastName) {
        givenABookingExistsFor(firstName, lastName, PRICE, DEPOSIT_PAID, CHECK_IN, CHECK_OUT);

        givenABookingExistsFor(firstName, lastName, PRICE, DEPOSIT_NOT_PAID, CHECK_IN, CHECK_OUT);
    }

    private void givenUserClicksOnTheCalenderFor(int checkIn, int checkOut) {
        pickBookingDateTask.clickOnCalenderCurrentMonthForCheckIn(checkIn);

        pickBookingDateTask.clickOnCalenderCurrentMonthForCheckOut(checkOut);
    }

    private void givenUserClicksOnTheCalenderToCheckInForNextMonth(int checkIn) {
        pickBookingDateTask.clickOnCalenderCheckInMonth(1, checkIn);
    }

    private void givenUserClicksOnTheCalenderToCheckOutForNextMonth(int checkOut) {
        pickBookingDateTask.clickOnCalenderCheckOutMonth(1, checkOut);
    }

    //Whens
    private void whenUserDeletesBooking(String firstName, String lastName, String price, String deposit,
                                        String checkIn, String checkOut) {
        cancelBookingTask.deleteBookingFor(firstName, lastName, price, deposit, checkIn, checkOut);
    }

    //Thens
    private void thenBookingIsDeletedFor(String firstName, String lastName, String price, String deposit,
                                         String checkIn, String checkOut) {
        checkBookingTask.assertBookingIsDeleted(firstName, lastName, price, deposit, checkIn, checkOut);
    }

    private void thenDuplicateBookingsExistFor(String firstName, String lastName, String price, String deposit,
                                               String checkIn, String checkOut) {
        checkBookingTask.assertCountDuplicateBookings(firstName, lastName, price, deposit,
                checkIn, checkOut, NUMBER_OF_DUPLICATES);
    }

    private void thenThereAreMultipleReservationsBookedFor(String firstName, String lastName) {
        checkBookingTask.assertCountDuplicateUniqueBookings(firstName, lastName, NUMBER_OF_DUPLICATES);
    }

}
