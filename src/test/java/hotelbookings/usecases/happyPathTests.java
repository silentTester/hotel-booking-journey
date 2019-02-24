package hotelbookings.usecases;

import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static hotelbookings.journey.tasks.cancelBookingTask.cancelBookingFor;
import static hotelbookings.journey.tasks.checkBookingTask.*;
import static hotelbookings.journey.tasks.pickBookingDatesTask.clickOnCalenderCheckInFor;
import static hotelbookings.journey.tasks.pickBookingDatesTask.clickOnCalenderCheckOutFor;

public class happyPathTests extends AutomatedTests {

    private final String LAST_NAME = "HAPPY_PATH";
    private final String UNIQUE_CHECK_IN_DATE = "2019-11-11";
    private final String UNIQUE_CHECK_OUT_DATE = "2019-11-13";
    private final int NUMBER_OF_DUPLICATES = 2;
    private final int CHECK_IN_DAY = 21;
    private final int CHECK_OUT_DAY = 23;
    private final int CURRENT_MONTH = 0;
    private final int NEXT_MONTH = 1;

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
        givenABookingExistsFor(randomFirstName, LAST_NAME, PRICE, DEPOSIT_PAID, CHECK_IN_DATE, CHECK_OUT_DATE);

        whenUserDeletesBooking(randomFirstName, LAST_NAME, PRICE, DEPOSIT_PAID, CHECK_IN_DATE, CHECK_OUT_DATE);

        thenBookingIsDeletedFor(randomFirstName, LAST_NAME, PRICE, DEPOSIT_PAID, CHECK_IN_DATE, CHECK_OUT_DATE);
    }

    @Test
    public void shouldMakeDuplicateBookings() {
        lastName = "HAPPY_PATH_DUPLICATE";

        givenDuplicateReservationsFor(randomFirstName, lastName, PRICE, DEPOSIT_NOT_PAID, CHECK_IN_DATE, CHECK_OUT_DATE);

        thenDuplicateBookingsExistFor(randomFirstName, lastName, PRICE, DEPOSIT_NOT_PAID, CHECK_IN_DATE, CHECK_OUT_DATE);
    }

    @Test
    public void shouldMakeMultipleUniqueBookings() {
        lastName = "HAPPY_PATH_UNIQUE";

        givenMultipleUniqueReservationsFor(randomFirstName, lastName);

        thenThereAreMultipleReservationsBookedFor(randomFirstName, lastName);
    }

    @Test
    public void shouldMakeBookingUsingCalendar() {
        lastName = "HAPPY_PATH_CALENDAR";

        givenUserFillsInBookingForm(randomFirstName, lastName, PRICE, DEPOSIT_PAID);
        givenUserClicksOnCalenderCheckInFor(CHECK_IN_DAY, CURRENT_MONTH);
        givenUserClicksOnCalenderCheckOutFor(CHECK_OUT_DAY, CURRENT_MONTH);

        whenUserSavesBooking();

        thenBookingIsSavedFor(randomFirstName, lastName, PRICE, DEPOSIT_PAID,
                formatDate(CHECK_IN_DAY, CURRENT_MONTH),
                formatDate(CHECK_OUT_DAY, CURRENT_MONTH));
    }

    @Test
    public void shouldMakeBookingUsingCalendarForNextMonth() {
        lastName = "HAPPY_PATH_CALENDAR_MONTH";

        givenUserFillsInBookingForm(randomFirstName, lastName, PRICE, DEPOSIT_NOT_PAID);
        givenUserClicksOnCalenderCheckInFor(CHECK_IN_DAY, NEXT_MONTH);
        givenUserClicksOnCalenderCheckOutFor(CHECK_OUT_DAY, NEXT_MONTH);

        whenUserSavesBooking();

        thenBookingIsSavedFor(randomFirstName, lastName, PRICE, DEPOSIT_NOT_PAID,
                formatDate(CHECK_IN_DAY, NEXT_MONTH),
                formatDate(CHECK_OUT_DAY, NEXT_MONTH));
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

        givenABookingExistsFor(firstName, lastName, PRICE, DEPOSIT_NOT_PAID, UNIQUE_CHECK_IN_DATE, UNIQUE_CHECK_OUT_DATE);
    }

    private void givenUserClicksOnCalenderCheckInFor(int checkIn, int month) {
        clickOnCalenderCheckInFor(checkIn, month);
    }

    private void givenUserClicksOnCalenderCheckOutFor(int checkOut, int month) {
        clickOnCalenderCheckOutFor(checkOut, month);
    }

    //Whens
    private void whenUserDeletesBooking(String firstName, String lastName, String price, String deposit,
                                        String checkIn, String checkOut) {
        cancelBookingFor(firstName, lastName, price, deposit, checkIn, checkOut);
    }

    //Thens
    private void thenBookingIsDeletedFor(String firstName, String lastName, String price, String deposit,
                                         String checkIn, String checkOut) {
        assertDeletedBooking(firstName, lastName, price, deposit, checkIn, checkOut);
    }

    private void thenDuplicateBookingsExistFor(String firstName, String lastName, String price, String deposit,
                                               String checkIn, String checkOut) {
        assertNumberDuplicateBookings(firstName, lastName, price, deposit, checkIn, checkOut, NUMBER_OF_DUPLICATES);
    }

    private void thenThereAreMultipleReservationsBookedFor(String firstName, String lastName) {
        assertNumberUniqueBookings(firstName, lastName, NUMBER_OF_DUPLICATES);
    }

}
