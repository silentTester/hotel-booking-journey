package hotelbookings.journey.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static hotelbookings.journey.actions.WaitingAction.countAllBookings;

public class CountingAction {

    public static Boolean isManyBookings(By selector, int expectedCount) {
        List<WebElement> bookingCount = countAllBookings(selector, expectedCount);

        if (bookingCount.size() != expectedCount) {
            return false;
        }

        return true;
    }
}
