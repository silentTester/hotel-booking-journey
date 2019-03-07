package hotelbookings.journey.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ReadingAction {

    private static WebDriver driver;

    public static String getTextFrom(By selector) {
        return driver.findElement(selector).getText();
    }

    public static String getAttributeIdFrom(By selector) {
        return driver.findElement(selector).getAttribute("id");
    }

    public static void setWebDriver(WebDriver driver) {
        ReadingAction.driver = driver;
    }
}
