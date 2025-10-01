package utilities;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.function.Function;

import static org.testng.Assert.assertTrue;

public class ElementFunctionality {

    private WebDriver driver;
    public String Device;

    public ElementFunctionality(WebDriver driver, String device) {
        this.driver = driver;
        this.Device = device;
    }

    /**
     * Wait until the page is fully loaded
     */
    public void waitForPageLoad(WebDriver driver, int timeoutSeconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds)).until(
                    webDriver -> ((JavascriptExecutor) webDriver)
                            .executeScript("return document.readyState").equals("complete")
            );
        } catch (TimeoutException e) {
            String message = "Page did not fully load within " + timeoutSeconds + " seconds.";
            System.err.println(message);
            Assert.fail(message);
        }
    }

    /**
     * Verify that an element is visible and interactable
     *
     * @param element        WebElement to check
     * @param name           Name for logging
     * @param timeoutSeconds Maximum wait time
     * @return
     */
    public boolean verifyBrowserElement(WebElement element, String name, int timeoutSeconds) {
        try {
            FluentWait<WebDriver> wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(timeoutSeconds))
                    .pollingEvery(Duration.ofSeconds(1))
                    .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);

            wait.until((Function<WebDriver, Boolean>) d -> element.isDisplayed() && element.getSize().getWidth() > 0);

            System.out.println(name + " displayed on " + Device);
            utilities.ExtentTestManager.getTest().log(Status.PASS, name + " displayed on " + Device);

        } catch (TimeoutException e) {
            String message = name + " could not be interacted with within " + timeoutSeconds + " seconds. Environment might be slow.";
            System.err.println(message);
            utilities.ExtentTestManager.getTest().log(Status.FAIL, message);
            Assert.fail(message);
        }
        return false;
    }


}