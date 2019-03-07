package hotelbookings.journey.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TypingAction {

    private static WebDriver driver;

    public static void textInput(By selector, String text) {
        driver.findElement(selector).sendKeys(text);
    }

    public static void setWebDriver(WebDriver driver) {
        TypingAction.driver = driver;
    }

}
