package hotelbookings.journey.actions;

import hotelbookings.usecases.AutomatedTests;
import org.openqa.selenium.By;

public class ReadingAction extends AutomatedTests {

    public static String getTextFrom(By selector) {
        return driver.findElement(selector).getText();
    }

    public static String getAttributeIdFrom(By selector) {
        return driver.findElement(selector).getAttribute("id");
    }

}
