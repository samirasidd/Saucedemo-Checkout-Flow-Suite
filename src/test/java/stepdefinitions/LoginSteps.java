package stepdefinitions;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utilities.DriverSetup;

public class LoginSteps {

    // Locators
    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    @Given("I am on the login page")
    public void loadLoginPage() {
        WebDriver driver = DriverSetup.getDriver();
        driver.get("https://www.saucedemo.com/");
    }

    @When("I login with {string} and {string}")
    public void performLogin(String username, String password) {
        WebDriver driver = DriverSetup.getDriver();
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    @Then("I should see the inventory page")
    public void verifyInventoryPage() {
        WebDriver driver = DriverSetup.getDriver();
        Assert.assertTrue(
                driver.getCurrentUrl().contains("/inventory.html"),
                "Not redirected to inventory page"
        );
    }

    @Then("I should see error {string}")
    public void verifyErrorMessage(String error) {
        WebDriver driver = DriverSetup.getDriver();
        String actualError = driver.findElement(errorMessage).getText();
        Assert.assertEquals(actualError, error);
    }
}