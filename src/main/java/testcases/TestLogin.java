package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;
import utilities.DriverSetup;

public class TestLogin extends DriverSetup {


    @Test(description = "Valid login redirects to inventory")
    public void testValidLogin() {
        MainPage mainPage = new MainPage();
        mainPage.loadPage();
        mainPage.login("standard_user", "secret_sauce");

        Assert.assertTrue(mainPage.isOnInventoryPage(),
                "Login failed - not redirected to inventory");
    }

    @Test(description = "Invalid login shows error")
    public void testInvalidLogin() {
        MainPage mainPage = new MainPage();
        mainPage.loadPage();
        mainPage.login("locked_out_user", "wrong_password");

        Assert.assertTrue(mainPage.getErrorMessage().contains("Epic sadface"),
                "Error message not displayed for invalid login");
    }
}