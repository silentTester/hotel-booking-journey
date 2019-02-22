package hotelbookings.journey.tasks;

import hotelbookings.AutomatedTests;
import hotelbookings.journey.actions.ClickingAction;
import hotelbookings.journey.actions.TypingAction;
import hotelbookings.journey.screen.HotelPage;

public class makeBookingTask extends AutomatedTests {

    public static void saveBookingFor(String firstname, String lastname, String price, String deposit, String checkIn, String checkOut) {
        TypingAction.textInput(HotelPage.setFirstName, firstname);
        TypingAction.textInput(HotelPage.setLastName, lastname);
        TypingAction.textInput(HotelPage.setPrice, price);
        TypingAction.textInput(HotelPage.setDeposit, deposit);
        TypingAction.textInput(HotelPage.setCheckIn, checkIn);
        TypingAction.textInput(HotelPage.setCheckOut, checkOut);

        ClickingAction.clickOn(HotelPage.saveButton);
    }

    public static void fillsInFormWithoutDates(String firstName, String lastName, String price, String deposit) {
        TypingAction.textInput(HotelPage.setFirstName, firstName);
        TypingAction.textInput(HotelPage.setLastName, lastName);
        TypingAction.textInput(HotelPage.setPrice, price);
        TypingAction.textInput(HotelPage.setDeposit, deposit);
    }

    public static void clickSave() {
        ClickingAction.clickOn(HotelPage.saveButton);
    }

}
