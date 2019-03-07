package hotelbookings.journey.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DisplayedAction {

    private static WebDriver driver;

    public static Boolean isDisplayed(By selector) {
        return driver.findElement(selector).isDisplayed();
    }

    public static void setWebDriver(WebDriver driver) {
        DisplayedAction.driver = driver;
    }
}
