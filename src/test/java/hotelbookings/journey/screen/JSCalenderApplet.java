package hotelbookings.journey.screen;

import org.openqa.selenium.By;

public class JSCalenderApplet {

    private static String getDayBy = "//a[text()='";
    private static By selectorNextMonth = By.xpath("//*[@class=\"ui-icon ui-icon-circle-triangle-e\"]");
    private static By selectorPreviousMonth = By.xpath("//*[@class=\"ui-icon ui-icon-circle-triangle-w\"]");

    public static By getCalenderDay(int day) {
        String selector = getDayBy + day + "']";

        return By.xpath(selector);
    }

    public static By nextCalenderMonth() {
        return selectorNextMonth;
    }

    public static By previousCalenderMonth() {
        return selectorPreviousMonth;
    }
}
