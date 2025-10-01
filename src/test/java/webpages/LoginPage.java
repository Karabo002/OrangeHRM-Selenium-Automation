


package webpages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import utilities.ExtentTestManager;
import com.aventstack.extentreports.Status;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton = By.cssSelector("button.orangehrm-login-button");
    private By loginContainer = By.cssSelector("div.login-container"); // container to ensure page rendered

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Wait for element with FluentWait
    private WebElement waitForElement(By locator, int maxWaitSeconds, int pollMillis) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(maxWaitSeconds))
                .pollingEvery(Duration.ofMillis(pollMillis))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
        return wait.until(driver -> driver.findElement(locator));
    }

    // Open URL and wait for login page container
    public void openUrl(String url) {
        int retries = 2; // retry if page renders blank
        for (int i = 0; i < retries; i++) {
            driver.get(url);
            try {
                waitForElement(loginContainer, 120, 500); // max 2 min
                ExtentTestManager.getTest().log(Status.INFO, "Login page loaded successfully.");
                break;
            } catch (TimeoutException e) {
                if (i == retries - 1) {
                    String failShot = takeScreenshot("LoginPageFailed");
                    ExtentTestManager.getTest().log(Status.FAIL, "Login page did not load after retries: " + e.getMessage())
                            .addScreenCaptureFromPath(failShot);
                    throw e;
                }
                System.out.println("Login page not rendered, retrying...");
            }
        }
    }

    // Login method with FluentWait
    public void login(String username, String password) {
        if (username == null || password == null) {
            throw new IllegalArgumentException("Username or password is null. Check your config file.");
        }

        try {
            WebElement userField = waitForElement(usernameField, 120, 500); // max 2 min, poll 0.5s
            userField.clear();
            userField.sendKeys(username);

            WebElement passField = waitForElement(passwordField, 120, 500);
            passField.clear();
            passField.sendKeys(password);

            WebElement loginBtn = waitForElement(loginButton, 120, 500);
            loginBtn.click();

            String screenshotPath = takeScreenshot("LoginSuccess");
            ExtentTestManager.getTest().log(Status.PASS, "Login successful for user: " + username)
                    .addScreenCaptureFromPath(screenshotPath);

        } catch (TimeoutException e) {
            String failShot = takeScreenshot("LoginFailed");
            ExtentTestManager.getTest().log(Status.FAIL,
                            "Login page elements not visible after 2 minutes: " + e.getMessage())
                    .addScreenCaptureFromPath(failShot);
            throw e;
        }
    }

    // Screenshot helper
    private String takeScreenshot(String fileName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        String dest = "test-output/screenshots/" + fileName + ".png";
        try {
            FileUtils.copyFile(src, new File(dest));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dest;
    }
}