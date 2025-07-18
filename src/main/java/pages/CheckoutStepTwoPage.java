package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static utilities.DriverSetup.getDriver;

public class CheckoutStepTwoPage extends BasePage {

    private final By totalAmount = By.xpath("//div[@class='summary_total_label']");
    private final By finishButton = By.id("finish");

    public CheckoutStepTwoPage() {
        super();
    }

    public boolean isTotalAmountDisplayed() {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(totalAmount))
                .isDisplayed();
    }

    public void clickFinish() {
        getDriver().findElement(finishButton).click();
    }
}