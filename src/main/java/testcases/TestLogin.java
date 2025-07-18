package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;
import utilities.DriverSetup;

public class TestLogin extends DriverSetup {

    @Test(description = "Valid login redirects to inventory")
    public void testValidLogin() {
        MainPage mainPage = new MainPage();

        // 1. Initial page load
        mainPage.loadPage();
        mainPage.takeScreenshot("1-Login_page_loaded");

        // 2. After successful login
        mainPage.login("standard_user", "secret_sauce");
        mainPage.takeScreenshot("2-After_successful_login");

        // 3. On inventory page verification
        Assert.assertTrue(mainPage.isOnInventoryPage(),
                "Login failed - not redirected to inventory");
        mainPage.takeScreenshot("3-Inventory_page_verified");
    }

    @Test(description = "Invalid login shows error")
    public void testInvalidLogin() {
        MainPage mainPage = new MainPage();

        // 1. Initial page load
        mainPage.loadPage();
        mainPage.takeScreenshot("1-Login_page_loaded");

        // 2. After failed login attempt
        mainPage.login("locked_out_user", "wrong_password");
        mainPage.takeScreenshot("2-After_failed_login_attempt");

        // 3. Error message verification
        String errorMessage = mainPage.getErrorMessage();
        mainPage.takeScreenshot("3-Error_message_displayed: " + errorMessage);

        Assert.assertTrue(errorMessage.contains("Epic sadface"),
                "Error message not displayed for invalid login");
    }
}