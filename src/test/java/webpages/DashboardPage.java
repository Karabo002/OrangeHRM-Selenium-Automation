package webpages;

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

public class DashboardPage {

    private WebDriver driver;
    public String Device;
    public ElementFunctionality verifyElement;





    // Example locators (update these with your app's IDs or stable selectors)
    @FindBy(xpath ="//p[.='Time at Work']")
    private WebElement timeAtWorkCard;

    @FindBy(xpath ="//p[.='Employees on Leave Today']")
    private WebElement employeeLeavesToday;

    @FindBy(xpath ="//p[.='Quick Launch']")
    private WebElement quickLaunchCard;

    @FindBy(xpath ="//p[.='Employee Distribution by Sub Unit']")
    private WebElement employeeDistributionBySubUnitCard;

    public DashboardPage(WebDriver driver, String Device) {
            this.driver = driver;
            this.Device = Device;
            verifyElement = new ElementFunctionality(driver, Device);
            PageFactory.initElements(driver, this);



    }
    public void waitForPageToLoad(WebDriver driver, int timeoutSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds)).until(
                webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState")
                        .equals("complete")
        );
    }

        // Validate that main dashboard elements are displayed
    /*

     */

     /*
        public void verifyDashboardElements() {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            wait.until(ExpectedConditions.visibilityOfElementLocated(timeAtWorkCard));
            wait.until(ExpectedConditions.visibilityOfElementLocated(employeeLeavesToday));
            wait.until(ExpectedConditions.visibilityOfElementLocated(timesheets));

            ExtentTestManager.getTest().log(Status.PASS, "All main dashboard elements are visible.");
        }

 */

    public void dashboarderHeader() {
        try {
            // Wait for page load
            waitForPageToLoad(driver, 30);

            // Verify each header
            verifyElement.verifyBrowserElement(timeAtWorkCard, "Time At Work Header", 60);
            verifyElement.verifyBrowserElement(employeeLeavesToday, "Employees on Leave Today Header", 60);
            verifyElement.verifyBrowserElement(quickLaunchCard, "Quick Launch Header", 60);
            verifyElement.verifyBrowserElement(employeeDistributionBySubUnitCard, "Employee Distribution by Sub Unit Header", 10);

            ExtentTestManager.getTest().log(Status.PASS, "Dashboard headers are visible.");
        } catch (Exception e) {
            String message = "The environment took too long to interact with the dashboard: " + e.getMessage();
            System.err.println(message);
            ExtentTestManager.getTest().log(Status.FAIL, message);
            Assert.fail(message);
        }
    }



        // Verify some data on the dashboard (example: employee count)
        /*
        public void verifyEmployeeCount(int expectedCount) {
            String countText = driver.findElement(employeeCountCard).getText();
            int actualCount = Integer.parseInt(countText);
            if (actualCount == expectedCount) {
                ExtentTestManager.getTest().log(Status.PASS, "Employee count is correct: " + actualCount);
            } else {
                ExtentTestManager.getTest().log(Status.FAIL, "Employee count mismatch. Expected: " + expectedCount + ", Found: " + actualCount);
            }
        }

         */





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