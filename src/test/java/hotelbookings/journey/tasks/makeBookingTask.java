package hotelbookings.journey.tasks;

import static hotelbookings.journey.actions.ClickingAction.clickOn;
import static hotelbookings.journey.actions.TypingAction.textInput;
import static hotelbookings.journey.screen.HotelPage.*;

public class makeBookingTask {

    public static void saveBookingFor(String firstName, String lastName, String price, String deposit,
                                      String checkIn, String checkOut) {
        textInput(fieldFirstName, firstName);
        textInput(fieldLastName, lastName);
        textInput(fieldPrice, price);
        textInput(fieldDeposit, deposit);
        textInput(fieldCheckIn, checkIn);
        textInput(fieldCheckOut, checkOut);

        clickOn(buttonSave);
    }

    public static void fillsInFormWithoutDates(String firstName, String lastName, String price, String deposit) {
        textInput(fieldFirstName, firstName);
        textInput(fieldLastName, lastName);
        textInput(fieldPrice, price);
        textInput(fieldDeposit, deposit);
    }

    public static void clickSave() {
        clickOn(buttonSave);
    }

}
