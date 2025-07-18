package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static utilities.DriverSetup.getDriver;

public class CheckoutStepOnePage extends BasePage {

    private final By firstNameField = By.id("first-name");
    private final By lastNameField = By.id("last-name");
    private final By postalCodeField = By.id("postal-code");
    private final By continueButton = By.id("continue");

    public boolean isCheckoutInfoDisplayed() {
        return getDriver().findElement(firstNameField).isDisplayed() &&
                getDriver().findElement(lastNameField).isDisplayed() &&
                getDriver().findElement(postalCodeField).isDisplayed();
    }

    public void enterShippingInfo(String firstName, String lastName, String postalCode) {
        getDriver().findElement(firstNameField).sendKeys(firstName);
        getDriver().findElement(lastNameField).sendKeys(lastName);
        getDriver().findElement(postalCodeField).sendKeys(postalCode);
    }

    public void clickContinue() {
        getDriver().findElement(continueButton).click();
    }
}