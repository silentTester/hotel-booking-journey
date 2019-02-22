package hotelbookings.journey.actions;

import hotelbookings.usecases.AutomatedTests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class WaitingAction extends AutomatedTests {

    public static Boolean isBookingFound(By selector) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(selector)).size() > 0;
    }

    public static Boolean isBookingNotFound(By selector) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(selector));
    }

    public static List<WebElement> countAllBookings(By selector, int expectedCount) {
        return wait.until(ExpectedConditions.numberOfElementsToBe(selector, expectedCount));
    }

}
