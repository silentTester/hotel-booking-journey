package hotelbookings.journey.actions;

import hotelbookings.configuration.WebDriverConfig;
import org.openqa.selenium.By;

public class DisplayedAction extends WebDriverConfig {

    public static Boolean isDisplayed(By selector) {
        return driver.findElement(selector).isDisplayed();
    }
}
