package stepdefinitions;

import io.cucumber.java.Before;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utilities.DriverSetup;

public class LoginTestHooks {

    @Before(order = 0)
    public static void ensureDriverExists() {
        // Force initialization if driver is null
        if (DriverSetup.getDriver() == null) {
            DriverSetup.setDriver(new FirefoxDriver()); // Or your default browser
            DriverSetup.getDriver().manage().window().maximize();
        }
    }
}