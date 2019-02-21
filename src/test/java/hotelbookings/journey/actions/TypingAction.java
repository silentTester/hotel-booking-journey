package hotelbookings.journey.actions;

import hotelbookings.AutomatedTests;
import org.openqa.selenium.By;

public class TypingAction extends AutomatedTests {

    public static void textInput(By selector, String text) {
        driver.findElement(selector).sendKeys(text);
    }
}
