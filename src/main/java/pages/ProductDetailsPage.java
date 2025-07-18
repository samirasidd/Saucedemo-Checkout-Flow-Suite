package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static utilities.DriverSetup.getDriver;

public class ProductDetailsPage extends BasePage {

    // Locators
    //private final By productTitle = By.cssSelector("[data-test='inventory-details-name']");
    //private final By productPrice = By.cssSelector("[data-test='inventory-details-price']");
    private final By productDesc = By.cssSelector(".inventory_details_desc.large_size");
    private final By addToCartBtn = By.cssSelector("[data-test^='add-to-cart']");
   // private final By detailsContainer = By.id("inventory_item_container");

    // private final By productName = By.cssSelector("[data-test='inventory-item-name']");
   // private final By backButton = By.id("back-to-products");
    private final By cartBadge = By.cssSelector(".shopping_cart_badge");
    private final By cartLink = By.cssSelector(".shopping_cart_link");
    private final By removeButton = By.xpath("//button[@id='remove']");

    private final List<By> priceLocators = Arrays.asList(
            By.className("inventory_details_price"),
            By.cssSelector("div.inventory_details_price"),
            By.xpath("//div[contains(@class,'price')]"),
            By.xpath("//div[contains(text(),'$')]"),
            By.cssSelector("[data-test*='price']")
    );

    public String getNumericPrice() {
        WebElement priceElement = findPriceElement();
        String priceText = priceElement.getText();
        System.out.println("Located price text: " + priceText);
        return priceText.replaceAll("[^\\d.]", "");
    }

    public WebElement findPriceElement() {
        for (By locator : priceLocators) {
            try {
                WebElement element = new WebDriverWait(getDriver(), Duration.ofSeconds(5))
                        .until(ExpectedConditions.presenceOfElementLocated(locator));
                if (element.isDisplayed() && !element.getText().isEmpty()) {
                    System.out.println("Success with locator: " + locator);
                    return element;
                }
            } catch (Exception e) {
                System.out.println("Attempt failed with locator: " + locator);
            }
        }
        throw new NoSuchElementException("Price element not found with any strategy");
    }

    public String getPriceViaJavaScript() {
        String script = "const elements = [" +
                "  ...document.getElementsByClassName('inventory_details_price')," +
                "  ...document.querySelectorAll('[data-test*=\"price\"]')," +
                "  ...document.querySelectorAll('div:contains(\"$\")')" +
                "];" +
                "return elements.find(el => el.textContent.match(/\\d/))?.textContent;";

        return (String) ((JavascriptExecutor)getDriver()).executeScript(script);
    }


    public String getDescriptionText() {
        // Using the existing productDesc locator that uses data-test attribute
        return getElement(productDesc).getText();
    }

    public WebElement getAddToCartButton() {
        // Using the existing addToCartBtn locator that uses data-test attribute
        return getElement(addToCartBtn);
    }

    public void clickAddToCart() {
        WebElement addToCartButton = new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(addToCartBtn));
        addToCartButton.click();
    }

    public int getCartItemCount() {
        try {
            return Integer.parseInt(getDriver().findElement(cartBadge).getText());
        } catch (Exception e) {
            return 0; // Return 0 if badge is not present
        }
    }

    public void navigateToCart() {
        getDriver().findElement(cartLink).click();
    }

    public boolean isAddToCartVisible() {
        return getDriver().findElement(addToCartBtn).isDisplayed();
    }

    public boolean isRemoveButtonVisible() {
        return getDriver().findElement(removeButton).isDisplayed();
    }

}