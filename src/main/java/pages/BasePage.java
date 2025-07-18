package pages;

import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.ByteArrayInputStream;
import java.time.Duration;

import static utilities.DriverSetup.getDriver;

public class BasePage {

    public WebElement getElement(By locator) {
        return waitForElementToBeVisible(locator, 10); // Now uses wait by default
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
    public WebElement waitForElementToBeVisible(By locator, int timeoutInSeconds) {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForElementToBeClickable(By locator) {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }


    public void hoverOverElement(By locator) {
        Actions actions = new Actions(getDriver());
        WebElement element = waitForElementToBeVisible(locator, 10);
        actions.moveToElement(element).perform();
    }


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

    public Actions actions = new Actions(getDriver());

    // Core scroll methods
    public void scrollByPixels(int x, int y) {
        actions.scrollByAmount(x, y).build().perform();
    }

    public void clickWithScroll(WebElement element) {
        actions.scrollToElement(element)
                .click(element)
                .build()
                .perform();
    }

    public void clickWithScroll(By locator) {
        WebElement element = waitForElementToBeVisible(locator, 10);
        actions.scrollToElement(element)
                .click(element)
                .build()
                .perform();
    }
}