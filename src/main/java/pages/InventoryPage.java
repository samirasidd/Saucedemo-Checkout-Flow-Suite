package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

import static utilities.DriverSetup.getDriver;

public class InventoryPage extends BasePage {
    // Locators
    private final By productContainer = By.cssSelector("[data-test='inventory-item']");
    private final By productName = By.cssSelector("[data-test='inventory-item-name']");
    private final By productPrice = By.cssSelector("[data-test='inventory-item-price']");
    private final By inventoryContainer = By.id("inventory_container");
    private final By googleAlertFrame = By.cssSelector("iframe[src*='accounts.google.com']");
    private final By googleAlertButtons = By.xpath("//button[contains(.,'OK') or contains(.,'Accept') or contains(.,'No') or contains(.,'Cancel')]");
    private final By googleDismissButton = By.xpath("//button[contains(.,'No') or contains(.,'Cancel')]");


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

    public boolean isGooglePasswordAlertPresent() {
        return !getDriver().findElements(googleAlertFrame).isEmpty();
    }

    public void handleGooglePasswordAlert() {
        if (isGooglePasswordAlertPresent()) {
            try {
                WebElement frame = getDriver().findElement(googleAlertFrame);
                getDriver().switchTo().frame(frame);

                // Try to click any available button
                getDriver().findElements(googleAlertButtons)
                        .stream()
                        .findFirst()
                        .ifPresent(WebElement::click);

                getDriver().switchTo().defaultContent();
            } catch (NoSuchElementException | NoSuchFrameException e) {
                System.out.println("Error handling Google alert: " + e.getMessage());
            }
        }
    }

    /**
     * Dismisses Google password alert if present
     */
    public void dismissGooglePasswordAlert() {
        if (isGooglePasswordAlertPresent()) {
            try {
                WebElement frame = getDriver().findElement(googleAlertFrame);
                getDriver().switchTo().frame(frame);

                // Try to click dismiss buttons (No, Cancel, etc.)
                List<WebElement> buttons = getDriver().findElements(
                        By.xpath("//button[contains(.,'No') or contains(.,'Cancel')]")
                );
                if (!buttons.isEmpty()) {
                    buttons.get(0).click();
                }

                getDriver().switchTo().defaultContent();
            } catch (NoSuchElementException | NoSuchFrameException e) {
                System.out.println("Error dismissing Google alert: " + e.getMessage());
            }
        }
    }

    // Product Methods
    public int getProductCount() {
        return getDriver().findElements(productContainer).size();
    }

    public void viewProductDetails(int position) {
        List<WebElement> products = waitForElementsToBeVisible(productName, 10);
        validatePosition(position, products.size());
        clickWithScroll(products.get(position - 1));
    }

    public String getNthProductPrice(int position) {
        List<WebElement> products = getDriver().findElements(productContainer);
        validatePosition(position, products.size());
        return products.get(position - 1).findElement(productPrice).getText();
    }

    public boolean isPageLoaded() {
        try {
            return waitForElementToBeVisible(inventoryContainer, 10) != null;
        } catch (TimeoutException e) {
            return false;
        }
    }

    // Private Helpers
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
    public void viewProductByTestId(String testId) {
        By locator = By.cssSelector("[data-test='" + testId + "']");
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(locator))
                .click();
    }
}