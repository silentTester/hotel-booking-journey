package hotelbookings.journey.screen;

import org.openqa.selenium.By;

public class HotelPage extends JSCalenderApplet {
    public static By setFirstName = By.id("firstname");
    public static By setLastName = By.id("lastname");
    public static By setPrice = By.id("totalprice");
    public static By setDeposit = By.id("depositpaid");
    public static By setCheckIn = By.id("checkin");
    public static By setCheckOut = By.id("checkout");
    public static By saveButton = By.xpath("//input[@value=\" Save \"]");

    private static String getRowBy = "//div[contains(@class, 'row')][contains(., '";
    private static String andContains = "') and contains(., '";
    private static String getDeleteRowBy = "//input[@type='button' and @onclick='deleteBooking(";

    public static By getBookingSelector(String firstName, String lastName, String price, String deposit, String checkIn, String checkOut) {
        String selector = getRowBy + firstName + andContains + lastName + andContains + price +
                andContains + deposit + andContains + checkIn + andContains + checkOut + "')]";

        return By.xpath(selector);
    }

    public static By getBookingAttributeIdSelector(String firstName, String lastName, String price, String deposit,
                                                   String checkIn, String checkOut) {
        return getBookingSelector(firstName, lastName, price, deposit, checkIn, checkOut);
    }

    public static By getBookingSelectorUsingNames(String firstName, String lastName) {
        String selector = getRowBy + firstName + andContains + lastName + "')]";

        return By.xpath(selector);
    }

    public static By deleteBooking(String attributeId) {
        String selector = getDeleteRowBy + attributeId + ")']";

        return By.xpath(selector);
    }

}

