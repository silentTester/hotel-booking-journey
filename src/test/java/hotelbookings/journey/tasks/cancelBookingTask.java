package hotelbookings.journey.tasks;

import hotelbookings.journey.actions.ReadingAction;

import static hotelbookings.journey.actions.ClickingAction.clickOn;
import static hotelbookings.journey.screen.HotelPage.buttonDelete;
import static hotelbookings.journey.screen.HotelPage.selectorByReservation;

public class cancelBookingTask {

    public static void cancelBookingFor(String firstName, String lastName, String price, String deposit,
                                        String checkIn, String checkOut) {
        String attributeId = ReadingAction.getAttributeIdFrom(
                selectorByReservation(firstName, lastName, price, deposit, checkIn, checkOut));
        clickOn(buttonDelete(attributeId));
    }

}
