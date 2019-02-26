package hotelbookings.journey.actions;

import hotelbookings.configuration.WebDriverConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class WaitingAction extends WebDriverConfig {

    public static Boolean isBookingFound(By selector) {
        try {
            return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(selector)).size() > 0;
        } catch (TimeoutException ex) {
            return false;
        }
    }

    public static Boolean isManyBookingsFound(By selector, int expectedCount) {
        return wait.until(ExpectedConditions.numberOfElementsToBe(selector, expectedCount)).size() == expectedCount;
    }

    public static Boolean isBookingNotFound(By selector) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(selector));
    }

}
