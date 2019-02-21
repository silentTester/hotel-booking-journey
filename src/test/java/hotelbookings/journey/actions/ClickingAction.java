package hotelbookings.journey.actions;

import hotelbookings.AutomatedTests;
import org.openqa.selenium.By;

public class ClickingAction extends AutomatedTests {

    public static void clickOn(By selector) {
        driver.findElement(selector).click();
    }

}