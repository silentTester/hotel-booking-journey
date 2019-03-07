package hotelbookings.journey.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ClickingAction {

    private static WebDriver driver;

    public static void clickOn(By selector) {
        driver.findElement(selector).click();
    }

    public static void setWebDriver(WebDriver driver) {
        ClickingAction.driver = driver;
    }

}
