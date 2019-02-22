package hotelbookings.journey.screen;

import org.openqa.selenium.By;

public class JSCalenderApplet {

    private static String xpathDayBy = "//a[text()='";
    private static By arrowNextMonth = By.xpath("//*[@class=\"ui-icon ui-icon-circle-triangle-e\"]");
    private static By arrowPreviousMonth = By.xpath("//*[@class=\"ui-icon ui-icon-circle-triangle-w\"]");

    public static By selectorByCalenderDay(int day) {
        String selector = xpathDayBy + day + "']";
        return By.xpath(selector);
    }

    public static By nextCalenderMonth() {
        return arrowNextMonth;
    }

    public static By previousCalenderMonth() {
        return arrowPreviousMonth;
    }
}
