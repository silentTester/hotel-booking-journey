package hotelbookings.journey.tasks;

import hotelbookings.usecases.AutomatedTests;
import hotelbookings.journey.actions.CountingAction;
import hotelbookings.journey.actions.DisplayedAction;
import hotelbookings.journey.actions.ReadingAction;
import hotelbookings.journey.actions.WaitingAction;
import hotelbookings.journey.screen.HotelPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class checkBookingTask extends AutomatedTests {

    public static void assertBookingIsSaved(String firstName, String lastName, String price, String deposit,
                                            String checkIn, String checkOut) {
        WaitingAction.waitForVisibilityAfterRefresh(HotelPage.getBookingSelector(firstName, lastName, price, deposit,
                checkIn, checkOut));

        String expectedBooking = (firstName + " " + lastName + " " + price + " " + deposit + " " + checkIn + " " + checkOut);
        String savedBooking = ReadingAction.getTextFrom(HotelPage.getBookingSelector(firstName, lastName, price, deposit,
                checkIn, checkOut)).replaceAll("\n", " ");
        assertEquals(expectedBooking, savedBooking);

        String attributeId = ReadingAction.getAttributeIdFrom(HotelPage.getBookingAttributeIdSelector(firstName, lastName, price, deposit, checkIn, checkOut));
        assertTrue(DisplayedAction.displayedOn(HotelPage.buttonDelete(attributeId)));
    }

    public static void assertBookingIsDeleted(String firstName, String lastName, String price, String deposit,
                                              String checkIn, String checkOut) {
        Boolean bookingIsDeleted = WaitingAction.waitForInvisibilityAfterRefresh(HotelPage.getBookingSelector(firstName,
                lastName, price, deposit, checkIn, checkOut));

        assertTrue(bookingIsDeleted);
    }

    public static void assertCountDuplicateBookings(String firstName, String lastName, String price,
                                                    String deposit, String checkIn, String checkOut,
                                                    int expectedDuplicateCount) {
        boolean countDuplicateBookings = CountingAction.countBookings
                (HotelPage.getBookingSelector(firstName, lastName, price, deposit, checkIn, checkOut),
                        expectedDuplicateCount);

        assertTrue(countDuplicateBookings);
    }

    public static void assertCountDuplicateUniqueBookings(String firstName, String lastName, int expectedDuplicateCount) {
        boolean countDuplicateBookings = CountingAction.countBookings
                (HotelPage.getBookingSelectorUsingNames(firstName, lastName), expectedDuplicateCount);

        assertTrue(countDuplicateBookings);
    }

}
