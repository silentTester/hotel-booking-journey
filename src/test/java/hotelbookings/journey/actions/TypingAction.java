package hotelbookings.journey.actions;

import hotelbookings.configuration.WebDriverConfig;
import org.openqa.selenium.By;

public class TypingAction extends WebDriverConfig {

    public static void textInput(By selector, String text) {
        driver.findElement(selector).sendKeys(text);
    }
}
