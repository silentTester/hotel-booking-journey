package hotelbookings.journey.tasks;

import hotelbookings.usecases.AutomatedTests;
import hotelbookings.journey.actions.ClickingAction;
import hotelbookings.journey.actions.ReadingAction;
import hotelbookings.journey.screen.HotelPage;

public class cancelBookingTask extends AutomatedTests {

    public static void deleteBookingFor(String firstName, String lastName, String price, String deposit, String checkIn,
                                        String checkOut) {
        String attributeId = ReadingAction.getAttributeIdFrom(
                HotelPage.selectorByAttributeId(firstName, lastName, price, deposit, checkIn, checkOut));

        ClickingAction.clickOn(HotelPage.buttonDelete(attributeId));
    }

}
