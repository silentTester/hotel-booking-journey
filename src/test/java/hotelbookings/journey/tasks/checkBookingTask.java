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

    public static void assertBookingIsSaved(String firstName, String lastName, String price, String deposit,
                                            String checkIn, String checkOut) {
        assertTrue(WaitingAction.isBookingVisible(HotelPage.bookingSelectorUsingReservation(firstName, lastName, price, deposit,
                checkIn, checkOut)));

        String expectedBooking = (firstName + " " + lastName + " " + price + " " + deposit + " " + checkIn + " " + checkOut);
        String savedBooking = ReadingAction.getTextFrom(HotelPage.bookingSelectorUsingReservation(firstName, lastName, price, deposit,
                checkIn, checkOut)).replaceAll("\n", " ");
        assertEquals(expectedBooking, savedBooking);

        String attributeId = ReadingAction.getAttributeIdFrom(HotelPage.bookingAttributeIdSelector(firstName, lastName, price, deposit,
                checkIn, checkOut));
        assertTrue(DisplayedAction.isDisplayed(HotelPage.buttonDelete(attributeId)));
    }

    public static void assertBookingIsDeleted(String firstName, String lastName, String price, String deposit,
                                              String checkIn, String checkOut) {
        Boolean bookingIsDeleted = WaitingAction.isBookingInvisible(HotelPage.bookingSelectorUsingReservation(firstName,
                lastName, price, deposit, checkIn, checkOut));

        assertTrue(bookingIsDeleted);
    }

    public static void assertCountDuplicateBookings(String firstName, String lastName, String price,
                                                    String deposit, String checkIn, String checkOut,
                                                    int expectedDuplicateNumber) {
        boolean countDuplicateBookings = CountingAction.isManyBookings
                (HotelPage.bookingSelectorUsingReservation(firstName, lastName, price, deposit, checkIn, checkOut),
                        expectedDuplicateNumber);

        assertTrue(countDuplicateBookings);
    }

    public static void assertCountDuplicateUniqueBookings(String firstName, String lastName, int expectedDuplicateNumber) {
        boolean countDuplicateBookings = CountingAction.isManyBookings
                (HotelPage.bookingSelectorUsingName(firstName, lastName), expectedDuplicateNumber);

        assertTrue(countDuplicateBookings);
    }

    public static void assertInvalidBookingSaved(String firstName, String lastName) {
        assertTrue(WaitingAction.isBookingVisible(HotelPage.bookingSelectorUsingName(firstName, lastName)));

        String attributeId = ReadingAction.getAttributeIdFrom(HotelPage.bookingSelectorUsingName(firstName, lastName));

        assertTrue(DisplayedAction.isDisplayed(HotelPage.buttonDelete(attributeId)));
    }
}
