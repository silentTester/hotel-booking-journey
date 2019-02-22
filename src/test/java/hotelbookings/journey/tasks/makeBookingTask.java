package hotelbookings.journey.tasks;

import hotelbookings.usecases.AutomatedTests;
import hotelbookings.journey.actions.ClickingAction;
import hotelbookings.journey.actions.TypingAction;
import hotelbookings.journey.screen.HotelPage;

public class makeBookingTask extends AutomatedTests {

    public static void saveBookingFor(String firstname, String lastname, String price, String deposit, String checkIn, String checkOut) {
        TypingAction.textInput(HotelPage.fieldFirstName, firstname);
        TypingAction.textInput(HotelPage.fieldLastName, lastname);
        TypingAction.textInput(HotelPage.fieldPrice, price);
        TypingAction.textInput(HotelPage.fieldDeposit, deposit);
        TypingAction.textInput(HotelPage.fieldCheckIn, checkIn);
        TypingAction.textInput(HotelPage.fieldCheckOut, checkOut);

        ClickingAction.clickOn(HotelPage.buttonSave);
    }

    public static void fillsInFormWithoutDates(String firstName, String lastName, String price, String deposit) {
        TypingAction.textInput(HotelPage.fieldFirstName, firstName);
        TypingAction.textInput(HotelPage.fieldLastName, lastName);
        TypingAction.textInput(HotelPage.fieldPrice, price);
        TypingAction.textInput(HotelPage.fieldDeposit, deposit);
    }

    public static void clickSave() {
        ClickingAction.clickOn(HotelPage.buttonSave);
    }

}
