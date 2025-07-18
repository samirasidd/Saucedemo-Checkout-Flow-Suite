package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

import static utilities.DriverSetup.getDriver;

public class InventoryPage extends BasePage {
    // Locators
    //private final By productContainer = By.cssSelector("[data-test='inventory-item']");
    private final By productName = By.cssSelector("[data-test='inventory-item-name']");
   // private final By productPrice = By.cssSelector("[data-test='inventory-item-price']");
    private final By inventoryContainer = By.id("inventory_container");
 //   private final By googleAlertFrame = By.cssSelector("iframe[src*='accounts.google.com']");
   // private final By googleAlertButtons = By.xpath("//button[contains(.,'OK') or contains(.,'Accept') or contains(.,'No') or contains(.,'Cancel')]");
  // private final By googleDismissButton = By.xpath("//button[contains(.,'No') or contains(.,'Cancel')]");


    // Alert Handling Methods
    public boolean isAlertPresent() {
        try {
            getDriver().switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public String getAlertText() {
        try {
            return getDriver().switchTo().alert().getText();
        } catch (NoAlertPresentException e) {
            return "";
        }
    }

    public void acceptAlert() {
        if (isAlertPresent()) {
            getDriver().switchTo().alert().accept();
        }
    }

    public void dismissAlert() {
        if (isAlertPresent()) {
            getDriver().switchTo().alert().dismiss();
        }
    }

    public void viewProductDetails(int position) {
        List<WebElement> products = waitForElementsToBeVisible(productName, 10);
        validatePosition(position, products.size());
        clickWithScroll(products.get(position - 1));
    }

    public boolean isPageLoaded() {
        try {
            return waitForElementToBeVisible(inventoryContainer, 10) != null;
        } catch (TimeoutException e) {
            return false;
        }
    }


    private void validatePosition(int position, int totalProducts) {
        if (position < 1 || position > totalProducts) {
            throw new IllegalArgumentException(
                    String.format("Invalid position %d. Only %d products available",
                            position, totalProducts)
            );
        }
    }

    private List<WebElement> waitForElementsToBeVisible(By locator, int timeoutInSeconds) {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }
}