package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utilities.DriverSetup;

public class TestCheckoutForm extends DriverSetup {

    @Test(description = "Validate form submission with test data")
    public void testCheckoutFormSubmission() {
        // Initialize page objects
        MainPage mainPage = new MainPage();
        InventoryPage inventoryPage = new InventoryPage();
        ProductDetailsPage productDetailsPage = new ProductDetailsPage();
        CartPage cartPage = new CartPage();
        CheckoutStepOnePage checkoutStepOnePage = new CheckoutStepOnePage();
        CheckoutStepTwoPage checkoutStepTwoPage = new CheckoutStepTwoPage();

        try {
            // 1. Login and add item to cart
            mainPage.loadPage();
            mainPage.login("standard_user", "secret_sauce");
            inventoryPage.viewProductDetails(5); // 5th item
            productDetailsPage.clickAddToCart();

            // 2. Navigate to checkout
            productDetailsPage.navigateToCart();
            cartPage.clickCheckoutButton();

            // 3. Fill out checkout form
            checkoutStepOnePage.enterShippingInfo("Alex", "Morgan", "1212");
            checkoutStepOnePage.clickContinue();

            // 4. Verify navigation to payment summary
            Assert.assertTrue(getDriver().getCurrentUrl().contains("/checkout-step-two.html"),
                    "Should be redirected to checkout step two after form submission");

            // 5. Verify total amount is displayed
            Assert.assertTrue(checkoutStepTwoPage.isTotalAmountDisplayed(),
                    "Total amount including tax should be displayed");

        } catch (Exception e) {
            throw new RuntimeException("Test failed: " + e.getMessage(), e);
        }
    }
}