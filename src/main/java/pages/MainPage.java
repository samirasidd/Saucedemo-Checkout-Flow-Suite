package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

public class MainPage extends BasePage {
    // Essential elements for first test
    public String url = "https://www.waterstones.com/";
    public String title = "Buy books, stationery and gifts, online and in store | Waterstones";

    public By cookieAccept = By.xpath("//button[@aria-label='Accept All']");
    public By headerLogo = By.xpath("//a[@class='logo']");

    // Helper method for first test
    public void loadAndAcceptCookies() {
        try {
            // Step 1: Load page
            loadAPage(url);

            // Step 2: Accept cookies
            WebElement cookieBtn = waitForElementToBeClickable(cookieAccept, 5);
            cookieBtn.click();

            // Step 3: Verify cookie banner disappears
            waitForElementToDisappear(cookieAccept, 3);
        } catch (TimeoutException e) {
            System.out.println("Cookie acceptance not required: " + e.getMessage());
        }
    }

}