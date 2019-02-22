package hotelbookings.journey.tasks;

import static hotelbookings.journey.actions.CountingAction.isManyBookings;
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
        assertTrue(isBookingFound(selectorByReservation(firstName, lastName, price, deposit,
                checkIn, checkOut)));

        String expectedBooking = (firstName + " " + lastName + " " + price + " " + deposit + " " + checkIn + " " + checkOut);
        String savedBooking = getTextFrom(selectorByReservation(firstName, lastName, price, deposit,
                checkIn, checkOut)).replaceAll("\n", " ");
        assertEquals(expectedBooking, savedBooking);

        String attributeId = getAttributeIdFrom(selectorByAttributeId(firstName, lastName, price, deposit,
                checkIn, checkOut));
        assertTrue(isDisplayed(buttonDelete(attributeId)));
    }

    public static void assertDeletedBooking(String firstName, String lastName, String price, String deposit,
                                            String checkIn, String checkOut) {
        Boolean bookingDeleted = isBookingNotFound(selectorByReservation(firstName,
                lastName, price, deposit, checkIn, checkOut));

        assertTrue(bookingDeleted);
    }

    public static void assertNumberDuplicateBookings(String firstName, String lastName, String price,
                                                     String deposit, String checkIn, String checkOut, int numberBookings) {
        assertTrue(isManyBookings(selectorByReservation(firstName, lastName, price, deposit, checkIn, checkOut),
                numberBookings));
    }

    public static void assertNumberUniqueBookings(String firstName, String lastName, int numberBookings) {
        assertTrue(isManyBookings(selectorByName(firstName, lastName), numberBookings));
    }

    public static void assertInvalidSavedBooking(String firstName, String lastName) {
        assertTrue(isBookingFound(selectorByName(firstName, lastName)));

        String attributeId = getAttributeIdFrom(selectorByName(firstName, lastName));

        assertTrue(isDisplayed(buttonDelete(attributeId)));
    }

    public static void assertUnsavedBooking(String firstName, String lastName) {
        assertTrue(isBookingNotFound(selectorByName(firstName, lastName)));
    }

}
