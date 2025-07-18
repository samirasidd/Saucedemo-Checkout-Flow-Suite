package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;
import utilities.DriverSetup;

public class TestHomePage extends DriverSetup {


    @Test(description = "Verify homepage loading")
    public void testHomepageLoading() {
        MainPage mainPage = new MainPage();
        // Step 1: Open browser and navigate
        mainPage.loadPage();

        // Step 2: Verify title and logo
        Assert.assertEquals(mainPage.getPageTitle(), "Swag Labs",
                "Homepage title mismatch");
        Assert.assertTrue(mainPage.isLogoDisplayed(),
                "Logo not visible on homepage");
    }
}