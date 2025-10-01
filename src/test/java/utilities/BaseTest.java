package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
//import com.relevantcodes.extentreports.LogStatus;
import com.aventstack.extentreports.Status;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;


    protected ExtentReports extent;

        @BeforeSuite
        public void beforeSuite() {
            extent = ExtentManager.getInstance();
        }

        @BeforeMethod
        public void setUp(Method method) {
            // Start ExtentTest for this method
            ExtentTest test = extent.createTest(method.getName());
            ExtentTestManager.setTest(test);

            // Browser setup
            String browser = ConfigReader.get("BROWSER").toLowerCase();
            boolean headless = ConfigReader.get("HEADLESS").equalsIgnoreCase("true");

            switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if (headless) chromeOptions.addArguments("--headless");
                driver = new ChromeDriver(chromeOptions);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headless) firefoxOptions.addArguments("--headless");
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case "edge":
                try {
                    WebDriverManager.edgedriver().setup(); // auto-download
                } catch (Exception e) {
                    // fallback to manual path if WDM fails
                    System.setProperty("webdriver.edge.driver", "/usr/local/bin/msedgedriver");
                }
                EdgeOptions edgeOptions = new EdgeOptions();
                if (headless) edgeOptions.addArguments("--headless");

                else edgeOptions.addArguments("--start-maximized");
                driver = new EdgeDriver(edgeOptions);
                break;
            default:
                throw new RuntimeException("Browser not supported: " + browser);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        ExtentTest test = ExtentTestManager.getTest();

        if (result.getStatus() == ITestResult.FAILURE) {
            String path = ScreenshotUtil.takeScreenshot(driver, result.getName());
            if (path != null) test.log(Status.FAIL, "Test Failed").addScreenCaptureFromPath(path);
            else test.log(Status.FAIL, "Test Failed");
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            String path = ScreenshotUtil.takeScreenshot(driver, result.getName());
            if (path != null) test.log(Status.PASS, "Test Passed").addScreenCaptureFromPath(path);
            else test.log(Status.PASS, "Test Passed");
        }

        if (driver != null) driver.quit();

        ExtentTestManager.removeTest();
        extent.flush();
    }
}