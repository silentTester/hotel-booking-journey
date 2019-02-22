package hotelbookings.journey.tasks;

import hotelbookings.journey.screen.JSCalenderApplet;
import hotelbookings.usecases.AutomatedTests;

import hotelbookings.journey.actions.ClickingAction;
import hotelbookings.journey.screen.HotelPage;

public class pickBookingDateTask extends AutomatedTests {

    public static void clickOnCalenderCurrentMonthForCheckIn(int day) {
        ClickingAction.clickOn(HotelPage.setCheckIn);
        ClickingAction.clickOn(HotelPage.getCalenderDay(day));
    }

    public static void clickOnCalenderCurrentMonthForCheckOut(int day) {
        ClickingAction.clickOn(HotelPage.setCheckOut);
        ClickingAction.clickOn(HotelPage.getCalenderDay(day));
    }

    public static void clickOnCalenderCheckInMonth(int numberTimes, int day) {
        ClickingAction.clickOn(HotelPage.setCheckIn);

        for (int count = 0; count < numberTimes; count++) {
            ClickingAction.clickOn(JSCalenderApplet.nextCalenderMonth());
        }
        ClickingAction.clickOn(HotelPage.getCalenderDay(day));
    }

    public static void clickOnCalenderCheckOutMonth(int numberTimes, int day) {
        ClickingAction.clickOn(HotelPage.setCheckOut);

        for (int count = 0; count < numberTimes; count++) {
            ClickingAction.clickOn(JSCalenderApplet.nextCalenderMonth());
        }
        ClickingAction.clickOn(HotelPage.getCalenderDay(day));
    }

    public static void clickOnCalenderCheckInPreviousMonth(int numberTimes, int day) {
        ClickingAction.clickOn(HotelPage.setCheckIn);

        for (int count = 0; count < numberTimes; count++) {
            ClickingAction.clickOn(JSCalenderApplet.previousCalenderMonth());
        }
        ClickingAction.clickOn(HotelPage.getCalenderDay(day));
    }

}
