package hotelbookings.journey.tasks;

import hotelbookings.AutomatedTests;
import hotelbookings.journey.actions.ReadingAction;
import hotelbookings.journey.actions.WaitingAction;
import hotelbookings.journey.screen.HotelPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class checkBookingTask extends AutomatedTests {

    public static void assertBookingIsSaved(String firstName, String lastName, String price, String deposit, String checkIn, String checkOut) {
        String expectedBooking = (firstName + " " + lastName + " " + price + " " + deposit + " " + checkIn + " " + checkOut);

        WaitingAction.waitForVisibilityAfterRefresh(HotelPage.getBookingSelector(firstName, lastName, price, deposit,
                checkIn, checkOut));

        String savedBooking = ReadingAction.getTextFrom(HotelPage.getBookingSelector(firstName, lastName, price, deposit,
                checkIn, checkOut)).replaceAll("\n", " ");
        assertEquals(expectedBooking, savedBooking);
    }

    public static void assertBookingIsDeleted(String firstName, String lastName, String price, String deposit, String checkIn, String checkOut) {
        Boolean bookingIsDeleted = WaitingAction.waitForInvisibilityAfterRefresh(HotelPage.getBookingSelector(firstName,
                lastName, price, deposit, checkIn, checkOut));

        assertTrue(bookingIsDeleted);
    }

}
