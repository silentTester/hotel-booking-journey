package hotelbookings.journey.tasks;

import static hotelbookings.journey.actions.CountingAction.isManyBookingsFound;
import static hotelbookings.journey.actions.DisplayedAction.isDisplayed;
import static hotelbookings.journey.actions.ReadingAction.getAttributeIdFrom;
import static hotelbookings.journey.actions.ReadingAction.getTextFrom;
import static hotelbookings.journey.actions.WaitingAction.isBookingFound;
import static hotelbookings.journey.actions.WaitingAction.isBookingNotFound;
import static hotelbookings.journey.screen.HotelPage.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class checkBookingTask {

    public static void assertSavedBooking(String firstName, String lastName, String price, String deposit,
                                          String checkIn, String checkOut) {
        assertTrue(isBookingFound(selectorByReservation(firstName, lastName, price, deposit, checkIn, checkOut)));

        String expectedBooking = (firstName + " " + lastName + " " + price + " " + deposit + " " + checkIn + " " + checkOut);
        String savedBooking = getTextFrom(selectorByReservation(firstName, lastName, price, deposit, checkIn,
                checkOut)).replaceAll("\n", " ");

        assertEquals(expectedBooking, savedBooking);
        assertTrue(isDisplayed(buttonDelete(getAttributeIdFrom(selectorByAttributeId(firstName, lastName, price, deposit,
                checkIn, checkOut)))));
    }

    public static void assertDeletedBooking(String firstName, String lastName, String price, String deposit,
                                            String checkIn, String checkOut) {
        assertTrue(isBookingNotFound(selectorByReservation(firstName, lastName, price, deposit, checkIn, checkOut)));
    }

    public static void assertNumberDuplicateBookings(String firstName, String lastName, String price,
                                                     String deposit, String checkIn, String checkOut, int numberBookings) {
        assertTrue(isManyBookingsFound(selectorByReservation(firstName, lastName, price, deposit, checkIn, checkOut),
                numberBookings));
    }

    public static void assertNumberUniqueBookings(String firstName, String lastName, int numberBookings) {
        assertTrue(isManyBookingsFound(selectorByName(firstName, lastName),
                numberBookings));
    }

    public static void assertInvalidSavedBooking(String firstName, String lastName) {
        assertTrue(isBookingFound(selectorByName(firstName, lastName)));

        assertTrue(isDisplayed(buttonDelete(getAttributeIdFrom(selectorByName(firstName, lastName)))));
    }

    public static void assertUnsavedBooking(String firstName, String lastName) {
        assertTrue(isBookingNotFound(selectorByName(firstName, lastName)));
    }

}
