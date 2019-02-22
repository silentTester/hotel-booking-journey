package hotelbookings.journey.tasks;

import hotelbookings.journey.actions.CountingAction;
import hotelbookings.journey.actions.DisplayedAction;
import hotelbookings.journey.actions.ReadingAction;
import hotelbookings.journey.actions.WaitingAction;
import hotelbookings.journey.screen.HotelPage;
import hotelbookings.usecases.AutomatedTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class checkBookingTask extends AutomatedTests {

    public static void assertSavedBooking(String firstName, String lastName, String price, String deposit,
                                          String checkIn, String checkOut) {
        assertTrue(WaitingAction.isBookingFound(HotelPage.selectorByReservation(firstName, lastName, price, deposit,
                checkIn, checkOut)));

        String expectedBooking = (firstName + " " + lastName + " " + price + " " + deposit + " " + checkIn + " " + checkOut);
        String savedBooking = ReadingAction.getTextFrom(HotelPage.selectorByReservation(firstName, lastName, price, deposit,
                checkIn, checkOut)).replaceAll("\n", " ");
        assertEquals(expectedBooking, savedBooking);

        String attributeId = ReadingAction.getAttributeIdFrom(HotelPage.selectorByAttributeId(firstName, lastName, price, deposit,
                checkIn, checkOut));
        assertTrue(DisplayedAction.isDisplayed(HotelPage.buttonDelete(attributeId)));
    }

    public static void assertDeletedBooking(String firstName, String lastName, String price, String deposit,
                                            String checkIn, String checkOut) {
        Boolean bookingIsDeleted = WaitingAction.isBookingNotFound(HotelPage.selectorByReservation(firstName,
                lastName, price, deposit, checkIn, checkOut));

        assertTrue(bookingIsDeleted);
    }

    public static void assertNumberDuplicateBookings(String firstName, String lastName, String price,
                                                     String deposit, String checkIn, String checkOut,
                                                     int expectedDuplicateNumber) {
        boolean countDuplicateBookings = CountingAction.isManyBookings
                (HotelPage.selectorByReservation(firstName, lastName, price, deposit, checkIn, checkOut),
                        expectedDuplicateNumber);

        assertTrue(countDuplicateBookings);
    }

    public static void assertNumberUniqueBookings(String firstName, String lastName, int expectedDuplicateNumber) {
        boolean countDuplicateBookings = CountingAction.isManyBookings
                (HotelPage.selectorByName(firstName, lastName), expectedDuplicateNumber);

        assertTrue(countDuplicateBookings);
    }

    public static void assertInvalidSavedBooking(String firstName, String lastName) {
        assertTrue(WaitingAction.isBookingFound(HotelPage.selectorByName(firstName, lastName)));

        String attributeId = ReadingAction.getAttributeIdFrom(HotelPage.selectorByName(firstName, lastName));

        assertTrue(DisplayedAction.isDisplayed(HotelPage.buttonDelete(attributeId)));
    }

    public static void assertUnsavedBooking(String firstName, String lastName) {
        assertTrue(WaitingAction.isBookingNotFound(HotelPage.selectorByName(firstName, lastName)));
    }

}
