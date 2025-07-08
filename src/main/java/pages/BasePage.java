package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static utilities.DriverSetup.getDriver;

public class BasePage {

    public WebElement getElement(By locator) {
        return waitForElementToBeVisible(locator); // Now uses wait by default
    }

    public void clickOnElement(By locator) {
        waitForElementToBeClickable(locator).click();
    }

    public void writeOnElement(By locator, String text) {
        WebElement element = getElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    public boolean visibleState(By locator) {
        try {
            return getElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void loadAPage(String url) {
        getDriver().get(url);
    }

    public String getPageTitle() {
        return getDriver().getTitle();
    }

    public String getElementText(By locator) {
        return getElement(locator).getText();
    }

    // Wait methods
    public WebElement waitForElementToBeVisible(By locator) {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForElementToBeClickable(By locator) {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    // NEW: Hover functionality
    public void hoverOverElement(By locator) {
        Actions actions = new Actions(getDriver());
        WebElement element = waitForElementToBeVisible(locator);
        actions.moveToElement(element).perform();
    }

    // NEW: Hover and click (common pattern)
    public void hoverAndClick(By hoverLocator, By clickLocator) {
        hoverOverElement(hoverLocator);
        clickOnElement(clickLocator);
    }

    public WebElement waitForElementToBeClickable(By locator, int timeoutInSeconds) {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitForElementToDisappear(By locator, int timeoutInSeconds) {
        new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
}