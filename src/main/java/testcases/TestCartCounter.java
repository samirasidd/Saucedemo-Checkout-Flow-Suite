package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utilities.DriverSetup;

public class TestCartCounter extends DriverSetup {

    @Test(description = "SD_CART_05 - Verify cart counter increments when adding an item")
    public void testCartCounterIncrementsWhenAddingItem() {
        // Initialize page objects
        MainPage mainPage = new MainPage();
        InventoryPage inventoryPage = new InventoryPage();
        ProductDetailsPage productDetailsPage = new ProductDetailsPage();

        try {
            // 1. Login to application
            mainPage.loadPage();
            mainPage.login("standard_user", "secret_sauce");

            // 2. Navigate to product details (5th item)
            inventoryPage.viewProductDetails(5);

            // 3. Verify initial cart is empty
            Assert.assertEquals(productDetailsPage.getCartItemCount(), 0,
                    "Cart should be empty initially");

            // 4. Add item to cart
            productDetailsPage.clickAddToCart();

            // 5. Verify cart counter updates to 1
            Assert.assertEquals(productDetailsPage.getCartItemCount(), 1,
                    "Cart counter should show 1 after adding item");

            // 6. Verify Remove button appears
            Assert.assertTrue(productDetailsPage.isRemoveButtonVisible(),
                    "Remove button should be visible after adding to cart");

        } catch (Exception e) {
            throw new RuntimeException("Test failed: " + e.getMessage(), e);
        }
    }
}