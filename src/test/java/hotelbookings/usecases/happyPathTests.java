package hotelbookings.usecases;

import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static hotelbookings.journey.tasks.cancelBookingTask.cancelBookingFor;
import static hotelbookings.journey.tasks.checkBookingTask.*;
import static hotelbookings.journey.tasks.pickBookingDatesTask.*;


public class happyPathTests extends AutomatedTests {

    private final String LAST_NAME = "HAPPY_PATH";

    @Before
    public void setUpData() {
        randomFirstName = UUID.randomUUID().toString();
    }

    @Test
    public void shouldMakeABooking() {
        givenUserMakesBooking(randomFirstName, LAST_NAME, PRICE, DEPOSIT_PAID, CHECK_IN_DATE, CHECK_OUT_DATE);

        thenBookingIsSavedFor(randomFirstName, LAST_NAME, PRICE, DEPOSIT_PAID, CHECK_IN_DATE, CHECK_OUT_DATE);
    }

    @Test
    public void shouldCancelABooking() {
        lastName = "DELETE_HAPPY_PATH";

        givenABookingExistsFor(randomFirstName, lastName, PRICE, DEPOSIT_PAID, CHECK_IN_DATE, CHECK_OUT_DATE);

        whenUserDeletesBooking(randomFirstName, lastName, PRICE, DEPOSIT_PAID, CHECK_IN_DATE, CHECK_OUT_DATE);

        thenBookingIsDeletedFor(randomFirstName, lastName, PRICE, DEPOSIT_PAID, CHECK_IN_DATE, CHECK_OUT_DATE);
    }

    @Test
    public void shouldMakeDuplicateBookings() {
        lastName = "DUPLICATE_HAPPY_PATH";

        givenDuplicateReservationsFor(randomFirstName, lastName, PRICE, DEPOSIT_NOT_PAID, CHECK_IN_DATE, CHECK_OUT_DATE);

        thenDuplicateBookingsExistFor(randomFirstName, lastName, PRICE, DEPOSIT_NOT_PAID, CHECK_IN_DATE, CHECK_OUT_DATE);
    }

    @Test
    public void shouldMakeMultipleUniqueBookings() {
        lastName = "UNIQUE_HAPPY_PATH";

        givenMultipleUniqueReservationsFor(randomFirstName, lastName);

        thenThereAreMultipleReservationsBookedFor(randomFirstName, lastName);
    }

    @Test
    public void shouldMakeBookingUsingCalendar() {
        lastName = "CALENDAR_HAPPY_PATH";

        givenUserFillsInBookingForm(randomFirstName, lastName, PRICE, DEPOSIT_PAID);
        givenUserClicksOnTheCalenderFor(CHECK_IN_DAY, CHECK_OUT_DAY);

        whenUserSavesBooking();

        thenBookingIsSavedFor(randomFirstName, lastName, PRICE, DEPOSIT_PAID,
                currentMonthOf(CHECK_IN_DAY, CURRENT_MONTH), currentMonthOf(CHECK_OUT_DAY, CURRENT_MONTH));
    }

    @Test
    public void shouldMakeBookingUsingCalendarForNextMonth() {
        lastName = "CALENDAR_NEXT_MONTH_HAPPY_PATH";

        givenUserFillsInBookingForm(randomFirstName, lastName, PRICE, DEPOSIT_NOT_PAID);
        givenUserClicksOnTheCalenderToCheckInForNextMonth(CHECK_IN_DAY);
        givenUserClicksOnTheCalenderToCheckOutForNextMonth(CHECK_OUT_DAY);

        whenUserSavesBooking();

        thenBookingIsSavedFor(randomFirstName, lastName, PRICE, DEPOSIT_NOT_PAID,
                currentMonthOf(CHECK_IN_DAY, 1), currentMonthOf(CHECK_OUT_DAY, 1));
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
        givenABookingExistsFor(firstName, lastName, PRICE, DEPOSIT_PAID, CHECK_IN_DATE, CHECK_OUT_DATE);

        givenABookingExistsFor(firstName, lastName, PRICE, DEPOSIT_NOT_PAID, CHECK_IN_DATE, CHECK_OUT_DATE);
    }

    private void givenUserClicksOnTheCalenderFor(int checkIn, int checkOut) {
        clickOnCalenderCurrentMonthForCheckIn(checkIn);

        clickOnCalenderCurrentMonthForCheckOut(checkOut);
    }

    private void givenUserClicksOnTheCalenderToCheckInForNextMonth(int checkIn) {
        clickOnCalenderCheckInMonth(1, checkIn);
    }

    private void givenUserClicksOnTheCalenderToCheckOutForNextMonth(int checkOut) {
        clickOnCalenderCheckOutMonth(1, checkOut);
    }

    //Whens
    private void whenUserDeletesBooking(String firstName, String lastName, String price, String deposit,
                                        String checkIn, String checkOut) {
        cancelBookingFor(firstName, lastName, price, deposit, checkIn, checkOut);
    }

    //Thens
    private void thenBookingIsDeletedFor(String firstName, String lastName, String price, String deposit,
                                         String checkIn, String checkOut) {
        assertBookingDeleted(firstName, lastName, price, deposit, checkIn, checkOut);
    }

    private void thenDuplicateBookingsExistFor(String firstName, String lastName, String price, String deposit,
                                               String checkIn, String checkOut) {
        assertNumberDuplicateBookings(firstName, lastName, price, deposit,
                checkIn, checkOut, NUMBER_OF_DUPLICATES);
    }

    private void thenThereAreMultipleReservationsBookedFor(String firstName, String lastName) {
        assertNumberUniqueBookings(firstName, lastName, NUMBER_OF_DUPLICATES);
    }

}
