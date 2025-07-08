package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;
import utilities.DriverSetup;

public class TestMainPage extends DriverSetup {
    MainPage mainPage = new MainPage();

    @Test(description = "Verify homepage title matches expected")
    public void testMainPageTitle() {
        // Step 1: Load page and accept cookies
        mainPage.loadAndAcceptCookies();

        // Step 2: Verify title
        String actualTitle = mainPage.getPageTitle();
        Assert.assertEquals(actualTitle, mainPage.title,
                "Page title mismatch. Expected: " + mainPage.title + " but got: " + actualTitle);

        // Step 3: Verify logo visible (additional sanity check)
        Assert.assertTrue(mainPage.visibleState(mainPage.headerLogo),
                "Header logo not visible");
    }
}