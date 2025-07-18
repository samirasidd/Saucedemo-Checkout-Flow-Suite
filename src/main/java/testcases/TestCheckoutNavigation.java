package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utilities.DriverSetup;

public class TestCheckoutNavigation extends DriverSetup {

    @Test(description = "SD_CHECKOUT_06 - Ensure checkout page loads from cart")
    public void testCheckoutPageNavigation() {
        // Initialize page objects
        MainPage mainPage = new MainPage();
        InventoryPage inventoryPage = new InventoryPage();
        ProductDetailsPage productDetailsPage = new ProductDetailsPage();
        CartPage cartPage = new CartPage();
        CheckoutStepOnePage checkoutStepOnePage = new CheckoutStepOnePage();

        try {
            // 1. Login and add item to cart
            mainPage.loadPage();
            mainPage.login("standard_user", "secret_sauce");
            inventoryPage.viewProductDetails(5); // 5th item
            productDetailsPage.clickAddToCart();

            // 2. Navigate to cart
            productDetailsPage.navigateToCart();
            Assert.assertTrue(getDriver().getCurrentUrl().contains("/cart.html"),
                    "Should be on cart page after clicking cart icon");

            // 3. Click checkout button
            cartPage.clickCheckoutButton();
            Thread.sleep(3000);
            // 4. Verify navigation to checkout step one
            Assert.assertTrue(getDriver().getCurrentUrl().contains("/checkout-step-one.html"),
                    "Should be redirected to checkout page after clicking checkout");
            Assert.assertTrue(checkoutStepOnePage.isCheckoutInfoDisplayed(),
                    "Checkout information form should be displayed");

        } catch (Exception e) {
            throw new RuntimeException("Test failed: " + e.getMessage(), e);
        }
    }
}