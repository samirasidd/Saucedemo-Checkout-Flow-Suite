package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utilities.DriverSetup;

public class TestOrderConfirmation extends DriverSetup {

    @Test(description = "Validate order confirmation")
    public void testOrderConfirmation() {
        // Initialize page objects
        MainPage mainPage = new MainPage();
        InventoryPage inventoryPage = new InventoryPage();
        ProductDetailsPage productDetailsPage = new ProductDetailsPage();
        CartPage cartPage = new CartPage();
        CheckoutStepOnePage checkoutStepOnePage = new CheckoutStepOnePage();
        CheckoutStepTwoPage checkoutStepTwoPage = new CheckoutStepTwoPage();
        CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage();

        try {
            // 1. Login and add item to cart
            mainPage.loadPage();
            mainPage.login("standard_user", "secret_sauce");
            inventoryPage.viewProductDetails(5); // 5th item
            productDetailsPage.clickAddToCart();

            // 2. Complete checkout steps
            productDetailsPage.navigateToCart();
            cartPage.clickCheckoutButton();
            checkoutStepOnePage.enterShippingInfo("Alex", "Morgan", "1212");
            checkoutStepOnePage.clickContinue();

            // 3. Click Finish button
            checkoutStepTwoPage.clickFinish();

            // 4. Verify order confirmation
            Assert.assertTrue(checkoutCompletePage.isConfirmationMessageDisplayed(),
                    "Order confirmation message should be displayed");

            Assert.assertEquals(checkoutCompletePage.getConfirmationMessageText(),
                    "Thank you for your order!",
                    "Confirmation message text should match expected");

            // 5. Verify URL matches confirmation page
            Assert.assertTrue(getDriver().getCurrentUrl().contains("/checkout-complete.html"),
                    "Should be on checkout complete page after finishing order");

        } catch (Exception e) {
            throw new RuntimeException("Test failed: " + e.getMessage(), e);
        }
    }
}