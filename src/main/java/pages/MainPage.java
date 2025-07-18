package pages;

import org.openqa.selenium.By;
import static utilities.DriverSetup.getDriver;

public class MainPage extends BasePage {
    // Locators
    public By usernameField = By.id("user-name");
    public By passwordField = By.id("password");
    public By loginButton = By.id("login-button");
    public By logo = By.className("login_logo");
    public By errorMessage = By.cssSelector("h3[data-test='error']");

    public void loadPage() {
        getDriver().get("https://www.saucedemo.com/");
    }

    public void login(String username, String password) {
        writeOnElement(usernameField, username);
        writeOnElement(passwordField, password);
        clickOnElement(loginButton);
    }

    public boolean isLogoDisplayed() {
        return visibleState(logo);
    }

    public boolean isOnInventoryPage() {
        return getDriver().getCurrentUrl().contains("/inventory.html");
    }

    public String getErrorMessage() {
        return getElementText(errorMessage);
    }

}