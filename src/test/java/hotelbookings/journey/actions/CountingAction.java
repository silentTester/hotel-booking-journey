package hotelbookings.journey.actions;

import hotelbookings.usecases.AutomatedTests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static hotelbookings.journey.actions.WaitingAction.waitForVisibilityOfAllBookings;

public class CountingAction extends AutomatedTests {

    public static Boolean isManyBookings(By selector, int expectedCount) {
        List<WebElement> bookingCount = waitForVisibilityOfAllBookings(selector, expectedCount);

        if (bookingCount.size() != expectedCount) {
            return false;
        }

        return true;
    }
}
