package hotelbookings.configuration;

import hotelbookings.journey.actions.ClickingAction;
import hotelbookings.journey.actions.DisplayedAction;
import hotelbookings.journey.actions.ReadingAction;
import hotelbookings.journey.actions.TypingAction;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverConfig {

    private final String WEB_DRIVER = "webdriver.chrome.driver";
    private final String WEB_DRIVER_PATH = "/usr/local/bin/chromedriver";


    public WebDriver getWebDriver() {
        System.setProperty(WEB_DRIVER, WEB_DRIVER_PATH);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        WebDriver driver = new ChromeDriver(chromeOptions);
        createActions(driver);
        return driver;
    }

    private void createActions(WebDriver driver) {
        ClickingAction.setWebDriver(driver);
        DisplayedAction.setWebDriver(driver);
        ReadingAction.setWebDriver(driver);
        TypingAction.setWebDriver(driver);
    }

}
