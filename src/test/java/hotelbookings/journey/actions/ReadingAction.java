package hotelbookings.journey.actions;

import hotelbookings.configuration.WebDriverConfig;
import org.openqa.selenium.By;

public class ReadingAction extends WebDriverConfig {

    public static String getTextFrom(By selector) {
        return driver.findElement(selector).getText();
    }

    public static String getAttributeIdFrom(By selector) {
        return driver.findElement(selector).getAttribute("id");
    }

}
