package hotelbookings.journey.tasks;

import static hotelbookings.journey.actions.ClickingAction.clickOn;
import static hotelbookings.journey.screen.HotelPage.fieldCheckIn;
import static hotelbookings.journey.screen.HotelPage.fieldCheckOut;
import static hotelbookings.journey.screen.JSCalenderApplet.*;

public class pickBookingDatesTask {

    public static void clickOnCalenderCurrentMonthForCheckIn(int day) {
        clickOn(fieldCheckIn);
        clickOn(selectorByCalenderDay(day));
    }

    public static void clickOnCalenderCurrentMonthForCheckOut(int day) {
        clickOn(fieldCheckOut);
        clickOn(selectorByCalenderDay(day));
    }

    public static void clickOnCalenderCheckInMonth(int numberTimes, int day) {
        clickOn(fieldCheckIn);

        for (int index = 0; index < numberTimes; index++) {
            clickOn(nextCalenderMonth());
        }
        clickOn(selectorByCalenderDay(day));
    }

    public static void clickOnCalenderCheckOutMonth(int numberTimes, int day) {
        clickOn(fieldCheckOut);

        for (int index = 0; index < numberTimes; index++) {
            clickOn(nextCalenderMonth());
        }
        clickOn(selectorByCalenderDay(day));
    }

    public static void clickOnCalenderCheckInPreviousMonth(int numberTimes, int day) {
        clickOn(fieldCheckIn);

        for (int index = 0; index < numberTimes; index++) {
            clickOn(previousCalenderMonth());
        }
        clickOn(selectorByCalenderDay(day));
    }

}
