package utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;

public class ScreenshotUtil {

    public static String takeScreenshot(WebDriver driver, String name) {
        try {
            // Ensure the page is loaded
            new WebDriverWait(driver, Duration.ofSeconds(6))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
            Thread.sleep(500); // wait a bit for rendering

            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String path = "test-output/screenshots/" + name + "_" + System.currentTimeMillis() + ".png";

            Files.createDirectories(Paths.get("test-output/screenshots"));
            Files.copy(src.toPath(), Paths.get(path));

            System.out.println("✅ Screenshot saved: " + path);
            return path;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}