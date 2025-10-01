package webpages;

import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utilities.ElementFunctionality;
import utilities.ExtentTestManager;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utilities.ExtentTestManager;
import utilities.ElementFunctionality;



import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static org.openqa.selenium.By.xpath;

public class PIMPage {

        private WebDriver driver;
        public String Device;
        public ElementFunctionality verifyElement;
    public ElementFunctionality waitForPageLoad;



    // Example locators (update these with your app's IDs or stable selectors)
        @FindBy(xpath ="//a[contains(@href,'pim/viewPimModule')]//span[text()='PIM']")
        private WebElement pimButton;

        @FindBy(xpath ="//p[.='Employees on Leave Today']")
        private WebElement employeeLeavesToday;

        @FindBy(xpath ="//p[.='Quick Launch']")
        private WebElement quickLaunchCard;

        @FindBy(xpath ="//p[.='Employee Distribution by Sub Unit']")
        private WebElement employeeDistributionBySubUnitCard;

    public PIMPage(WebDriver driver, String Device) {
        this.driver = driver;
        this.Device = Device;
        verifyElement = new ElementFunctionality(driver, Device);
        PageFactory.initElements(driver, this);
    }

    public void clickPimButton() {
        String elementName = "PIM Button Main Menu Bar";
        try {
            waitForPageToLoad(driver, 30);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", pimButton);
            verifyElement.verifyBrowserElement(pimButton, elementName, 60);

            new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(pimButton));

            pimButton.click();
            ExtentTestManager.getTest().log(Status.PASS, elementName + " clicked successfully.");
            System.out.println(elementName + " clicked successfully on " + Device);

        } catch (Exception e) {
            handleFailure(elementName, e, "Failed to click");
        }
    }

    private void handleFailure(String elementName, Exception e, String reason) {
        String message = String.format("%s: %s. Reason: %s", elementName, e.getMessage(), reason);
        System.err.println(message);

        String screenshotPath = takeScreenshot(elementName.replace(" ", "_") + "_Error");
        try {
            ExtentTestManager.getTest().log(Status.FAIL, message)
                    .addScreenCaptureFromPath(screenshotPath);
        } catch (Exception ex) {
            System.err.println("Failed to attach screenshot: " + ex.getMessage());
        }

        Assert.fail(message);
    }

    private void waitForPageToLoad(WebDriver driver, int timeoutInSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds)).until(
                webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete")
        );
    }

    private String takeScreenshot(String fileName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        String destDir = "test-output/screenshots/";
        File dir = new File(destDir);
        if (!dir.exists()) dir.mkdirs();
        String dest = destDir + fileName + ".png";
        try {
            FileUtils.copyFile(src, new File(dest));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dest;
    }
}