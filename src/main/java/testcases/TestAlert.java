package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.MainPage;
import utilities.DriverSetup;

public class TestAlert extends DriverSetup {

    @Test(description = "Password change alert handling")
    public void testPasswordChangeAlert() throws InterruptedException {
        MainPage mainPage = new MainPage();
        InventoryPage inventoryPage = new InventoryPage();

        // 1. Load page and login
        mainPage.loadPage();
        mainPage.login("standard_user", "secret_sauce");

        // 2. Handle alert if present (non-blocking approach)
        try {
            if (inventoryPage.isAlertPresent()) {
                String alertText = inventoryPage.getAlertText();

                // 3. Validate alert content
                Assert.assertTrue(alertText.toLowerCase().contains("password") &&
                                alertText.toLowerCase().contains("data breach"),
                        "Alert text should mention password and data breach");

                // 4. Handle alert based on type
                if (alertText.contains("Google Password Manager")) {

                    // For Google alerts - just dismiss
                    inventoryPage.dismissAlert();
                    System.out.println("Dismissed Google password warning");
                } else {
                    // For other alerts - accept
                    inventoryPage.acceptAlert();
                }
            }
        } catch (Exception e) {
            System.out.println("No alert present or error handling alert: " + e.getMessage());
        }

        // 5. Verify successful navigation
        Assert.assertTrue(getDriver().getCurrentUrl().contains("/inventory.html"),
                "Login should succeed and redirect to inventory page");

        // 6. Additional verification of page load
        Assert.assertTrue(inventoryPage.isPageLoaded(),
                "Inventory page should be fully loaded");
    }
}