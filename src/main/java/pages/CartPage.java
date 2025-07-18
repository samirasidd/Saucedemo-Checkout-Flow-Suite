package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static utilities.DriverSetup.getDriver;

public class CartPage extends BasePage {

    private final By checkoutButton = By.id("checkout");
    //private final By cartItems = By.className("cart_item");


    public void clickCheckoutButton() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(checkoutButton))
                .click();
    }

}