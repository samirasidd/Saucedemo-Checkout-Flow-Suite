package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

public class DriverSetup {

    public static String browser_name = System.getProperty("browser", "chrome");

    private static final ThreadLocal<WebDriver> DRIVER_THREAD_LOCAL = new ThreadLocal<>();

    public static void setDriver(WebDriver driver){
        DriverSetup.DRIVER_THREAD_LOCAL.set(driver);
    }

    public static WebDriver getDriver(){
        return DRIVER_THREAD_LOCAL.get();
    }


    @BeforeMethod
    public void startBrowser(){
        WebDriver driver = getBrowser(browser_name);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        setDriver(driver);

    }

    @AfterMethod
    public void closeBrowser(){
        getDriver().quit();
    }

    public WebDriver getBrowser(String name) {
        if(name.equalsIgnoreCase("Chrome")){
            return new ChromeDriver();
        }
        else if(name.equalsIgnoreCase("Edge")){
            return new EdgeDriver();
        } else if (name.equalsIgnoreCase("Firefox")) {
            return new FirefoxDriver();
        }else{
            throw new RuntimeException("Browser is not available with given name: " + name);
        }
    }
}
