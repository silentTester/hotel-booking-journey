package hotelbookings.journey.actions;

import hotelbookings.usecases.AutomatedTests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CountingAction extends AutomatedTests {

    public static List<WebElement> countAllBookings(By selector, int expectedCount) {
        return wait.until(ExpectedConditions.numberOfElementsToBe(selector, expectedCount));
    }

    public static Boolean isManyBookingsFound(By selector, int expectedCount) {
        List<WebElement> bookingCount = countAllBookings(selector, expectedCount);

        if (bookingCount.size() != expectedCount) {
            return false;
        }

        return true;
    }
}
