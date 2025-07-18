package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static utilities.DriverSetup.getDriver;

public class CheckoutCompletePage extends BasePage {

    private final By confirmationMessage = By.xpath("//h2[normalize-space()='Thank you for your order!']");
    private final By backHomeButton = By.id("back-to-products");

    public CheckoutCompletePage() {
        super();
    }

    public boolean isConfirmationMessageDisplayed() {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(confirmationMessage))
                .isDisplayed();
    }

    public String getConfirmationMessageText() {
        return getDriver().findElement(confirmationMessage).getText();
    }

    public void clickBackHome() {
        getDriver().findElement(backHomeButton).click();
    }
}