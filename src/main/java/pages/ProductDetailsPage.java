package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static utilities.DriverSetup.getDriver;

public class ProductDetailsPage extends BasePage {

    // Locators - consistent approach (using data-test attributes)
    private final By productTitle = By.cssSelector("[data-test='inventory-details-name']");
    private final By productPrice = By.cssSelector("[data-test='inventory-details-price']");
    private final By productDesc = By.cssSelector(".inventory_details_desc.large_size");
    private final By addToCartBtn = By.cssSelector("[data-test^='add-to-cart']");
    private final By detailsContainer = By.id("inventory_item_container");

    public final By priceElement = By.cssSelector("[data-test='inventory-details-price']");
    private final By pageContainer = By.id("inventory_item_container");

    private final By productName = By.cssSelector("[data-test='inventory-item-name']");
    private final By backButton = By.id("back-to-products");
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


    public void waitForPageToLoad() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(15))
                .until(d -> {
                    WebElement container = d.findElement(detailsContainer);
                    return container.isDisplayed() &&
                            !d.findElement(productName).getText().isEmpty();
                });
    }

    public String getProductName() {
        return getDriver().findElement(productName).getText();
    }

    public String getPriceText() {
        return getDriver().findElement(priceElement).getText();
    }


    public void waitForPageContainer() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(pageContainer));
    }

    public void waitForPriceElement() {
        try {
            new WebDriverWait(getDriver(), Duration.ofSeconds(20))
                    .until(driver -> {
                        WebElement element = driver.findElement(priceElement);
                        System.out.println("[DEBUG] Found price element. Text: '" + element.getText() + "'");

                        if (!element.isDisplayed()) {
                            System.out.println("[DEBUG] Price element not displayed");
                            return false;
                        }

                        String priceText = element.getText();
                        boolean hasNumbers = priceText.matches(".*\\d.*");

                        System.out.println("[DEBUG] Price validation: " +
                                "Displayed=" + element.isDisplayed() + ", " +
                                "HasNumbers=" + hasNumbers);

                        return element.isDisplayed() && hasNumbers;
                    });
        } catch (TimeoutException e) {
            System.out.println("[DEBUG] Price element timeout. Current page source:");
            System.out.println(getDriver().getPageSource().substring(0, 1000) + "...");
            throw new RuntimeException("Failed to find valid price element after 20 seconds", e);
        }
    }


    public boolean isPageLoaded() {
        try {
            return getDriver().findElement(detailsContainer).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getProductPrice() {
        return waitForElementToBeVisible(productPrice, 15).getText();
    }

    public String getProductDescription() {
        return getElement(productDesc).getText();
    }

    public boolean isAddToCartEnabled() {
        return getElement(addToCartBtn).isEnabled();
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

    public void clickBackToProducts() {
        getDriver().findElement(backButton).click();
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