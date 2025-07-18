package testcases;

import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utilities.DriverSetup;

public class TestScrolling extends DriverSetup {

    @Test(description = "Verify product details with robust element location")
    public void testProductDetailsNavigation() {
        MainPage mainPage = new MainPage();
        InventoryPage inventoryPage = new InventoryPage();
        ProductDetailsPage detailsPage = new ProductDetailsPage();

        try {
            // 1. Load and login
            mainPage.loadPage();
            mainPage.login("standard_user", "secret_sauce");

            // 2. Handle alerts
            if (inventoryPage.isAlertPresent()) {
                inventoryPage.dismissAlert();
            }

            // 3. Verify inventory
            Assert.assertTrue(inventoryPage.isPageLoaded(), "Inventory page not loaded");

            // 4. Navigate to product
            int productIndex = 4; // Zero-based index for 5th item
            inventoryPage.viewProductDetails(productIndex + 1);

            // 5. Verify product details
            verifyPriceWithFallbacks(detailsPage);
            verifyDescriptionContainsOnesie(detailsPage);
            verifyAddToCartButtonEnabled(detailsPage);

        } catch (Exception e) {
            throw new RuntimeException("Test failed: " + e.getMessage(), e);
        }
    }

    private void verifyPriceWithFallbacks(ProductDetailsPage detailsPage) {
        try {
            String numericPrice = detailsPage.getNumericPrice();
            validatePrice(numericPrice);
        } catch (NoSuchElementException e) {
            try {
                String jsPrice = detailsPage.getPriceViaJavaScript();
                validatePrice(jsPrice.replaceAll("[^\\d.]", ""));
            } catch (Exception jsEx) {
                visualPriceValidation();
            }
        }
    }

    private void verifyDescriptionContainsOnesie(ProductDetailsPage detailsPage) {
        String description = detailsPage.getDescriptionText();
        Assert.assertNotNull(description, "Description should not be null");
        Assert.assertTrue(description.toLowerCase().contains("onesie"),
                "Description should contain 'onesie'. Actual description: " + description);
    }

    private void verifyAddToCartButtonEnabled(ProductDetailsPage detailsPage) {
        WebElement addToCartButton = detailsPage.getAddToCartButton();
        Assert.assertTrue(addToCartButton.isDisplayed(), "Add to cart button should be displayed");
        Assert.assertTrue(addToCartButton.isEnabled(), "Add to cart button should be enabled");
    }

    private void validatePrice(String numericPrice) {
        Assert.assertFalse(numericPrice.isEmpty(), "Price should contain numbers");
        double priceValue = Double.parseDouble(numericPrice);
        Assert.assertEquals(priceValue, 7.99, "Price should be $7.99");
    }

    private void visualPriceValidation() {
        throw new RuntimeException("Visual verification required - all automated price verification methods failed");
    }
}