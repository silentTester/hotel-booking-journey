package hotelbookings.journey.actions;

import hotelbookings.configuration.WebDriverConfig;
import org.openqa.selenium.By;

public class ClickingAction extends WebDriverConfig {

    public static void clickOn(By selector) {
        driver.findElement(selector).click();
    }

}
