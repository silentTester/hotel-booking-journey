package hotelbookings.journey.JavaScriptApplet;

import org.openqa.selenium.By;

public class CalendarJSApplet {

    private static String getDayBy = "//a[text()='";
    private static By selectorNextMonth = By.xpath("//*[@class=\"ui-icon ui-icon-circle-triangle-e\"]");

    public static By getCalenderDay(int day) {
        String selector = getDayBy + day + "']";

        return By.xpath(selector);
    }

    public static By nextMonth() {
        return selectorNextMonth;
    }
}
