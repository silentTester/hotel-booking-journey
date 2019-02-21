package hotelbookings.journey.actions;

import hotelbookings.AutomatedTests;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class WaitingAction extends AutomatedTests {

    public static void waitForVisibilityAfterRefresh(By selector) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(selector));
    }

    public static Boolean waitForInvisibilityAfterRefresh(By selector) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(selector));
    }

}
