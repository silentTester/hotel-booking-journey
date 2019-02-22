package hotelbookings.journey.actions;

import hotelbookings.usecases.AutomatedTests;
import org.openqa.selenium.By;

public class DisplayedAction extends AutomatedTests {

    public static Boolean isDisplayed(By selector) {
        return driver.findElement(selector).isDisplayed();
    }
}
