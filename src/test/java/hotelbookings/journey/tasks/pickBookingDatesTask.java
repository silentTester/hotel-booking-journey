package hotelbookings.journey.tasks;

import static hotelbookings.journey.actions.ClickingAction.clickOn;
import static hotelbookings.journey.screen.HotelPage.fieldCheckIn;
import static hotelbookings.journey.screen.HotelPage.fieldCheckOut;
import static hotelbookings.journey.screen.JSCalenderApplet.*;

public class pickBookingDatesTask {

    public static void clickOnCalenderCheckInFor(int day, int numberClicks) {
        clickOn(fieldCheckIn);

        for (int index = 0; index < numberClicks; index++) {
            clickOn(nextCalenderMonth());
        }
        clickOn(selectorByCalenderDay(day));
    }

    public static void clickOnCalenderCheckOutFor(int day, int numberClicks) {
        clickOn(fieldCheckOut);

        for (int index = 0; index < numberClicks; index++) {
            clickOn(nextCalenderMonth());
        }
        clickOn(selectorByCalenderDay(day));
    }

    public static void clickOnCalenderPreviousMonthCheckInFor(int day, int numberClicks) {
        clickOn(fieldCheckIn);

        for (int index = 0; index < numberClicks; index++) {
            clickOn(previousCalenderMonth());
        }
        clickOn(selectorByCalenderDay(day));
    }

    public static void clickOnCalenderPreviousMonthCheckOutFor(int day, int numberClicks) {
        clickOn(fieldCheckOut);

        for (int index = 0; index < numberClicks; index++) {
            clickOn(previousCalenderMonth());
        }
        clickOn(selectorByCalenderDay(day));
    }


}
