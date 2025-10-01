package webpages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utilities.ElementFunctionality;
import utilities.ExtentTestManager;

import java.time.Duration;
import java.util.List;

public class JobDetailsPage {


    private WebDriver driver;
    public String Device;
    public ElementFunctionality verifyElement;
    public ElementFunctionality waitForPageLoad;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    @FindBy(xpath = "//a[.='Job']")
    private WebElement jobTab;

    @FindBy(xpath = "//div[@class='oxd-date-input']/input[@class='oxd-input oxd-input--active']")
    private WebElement jobJoinedDateInput;

    @FindBy(xpath = "//div[@class='oxd-grid-3 orangehrm-full-width-grid']/div[2]//div[@class='oxd-select-text-input']")
    private WebElement jobTitleDropDown;

    @FindBy(xpath = "//div[@class='oxd-grid-3 orangehrm-full-width-grid']/div[4]//div[@class='oxd-select-text-input']")
    private WebElement jobCategoryDropDown;

    @FindBy(xpath = "//div[@class='oxd-grid-3 orangehrm-full-width-grid']/div[5]//div[@class='oxd-select-text-input']")
    private WebElement subUnitDropDown;

    @FindBy(xpath = " //div[@class='oxd-grid-3 orangehrm-full-width-grid']/div[6]//div[@class='oxd-select-text-input']")
    private WebElement locationDropDown;

    @FindBy(xpath = " //div[@class='oxd-grid-3 orangehrm-full-width-grid']/div[7]//div[@class='oxd-select-text-input']")
    private WebElement employmentStatusDropDown;

    @FindBy(xpath = "//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']")
    private WebElement secSaveBtn;

    @FindBy(xpath = "//div[@class='orangehrm-edit-employee-content']/div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']//div[@class='oxd-grid-3 orangehrm-full-width-grid']/div[1]//div[@class='oxd-select-text-input']")
    private WebElement nationalityDropDown;

    @FindBy(xpath = "//div[@class='orangehrm-edit-employee-content']//div[@class='oxd-grid-3 orangehrm-full-width-grid']/div[1]//div[@class='oxd-date-wrapper']//input[@class='oxd-input oxd-input--active']")
    private WebElement dateOfBirthInput;

   ;

    @FindBy(xpath = "//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']")
    private WebElement popupMessageEmployeeAdded;

    @FindBy(xpath = "//div[@class='--gender-grouped-field']/div[1]//span[@class='oxd-radio-input oxd-radio-input--active --label-right oxd-radio-input']")
    private WebElement maleCheckBox;

    @FindBy(xpath = " //button[@class='oxd-button oxd-button--medium oxd-button--text']")
    private WebElement addAttachmentBtn;

    @FindBy(xpath = "//div[@class='orangehrm-attachment']//input[@type='file']")
    private WebElement fileUploadInput;

    @FindBy(xpath = "//button[2]")
    private WebElement savefileUploadBtn;

    @FindBy(xpath = "//div[@class='oxd-table-card']/div[@class='oxd-table-row oxd-table-row--with-border']")
    private WebElement attachmentsTable;


    public JobDetailsPage(WebDriver driver, String Device) {
        this.driver = driver;
        this.Device = Device;
        verifyElement = new ElementFunctionality(driver, Device);
        PageFactory.initElements(driver, this);
    }

    public void navigateToJobDetailsTab() {


        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");
        //js.executeScript("arguments[0].scrollIntoView(true);", jobTab);
        verifyElement.verifyBrowserElement(jobTab, "Job Tab Successfully Validated", 60);
       // js.executeScript("arguments[0].scrollIntoView(true);", jobTab);
        // Move to the element using Actions (helps for hidden/styled inputs)
        Actions actions = new Actions(driver);
        actions.moveToElement(jobTab).perform();
         jobTab.click();
        System.out.println("Job Tab Clicked Successfully on " + Device);
        ExtentTestManager.getTest().log(Status.PASS, jobTab + " clicked successfully.");
        System.out.println(jobTab + " clicked successfully on " + Device);

    }

    public void enterJoinedDate(String date) {
        try {
            verifyElement.verifyBrowserElement(jobJoinedDateInput, "Job Joined Date", 30);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='oxd-form-loader']")));
            jobJoinedDateInput.clear();
            jobJoinedDateInput.sendKeys(date);
            ExtentTestManager.getTest().log(Status.PASS, "Job Joined Date: " + date);
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.FAIL, "Failed to enter Job Joined Date: " + e.getMessage());
            Assert.fail("Join Date entry failed");
        }
    }

    public void selectJobCategory(String Text) {
        verifyElement.verifyBrowserElement(jobCategoryDropDown, "jobCategoryDropDown", 60);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Click dropdown
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='oxd-grid-3 orangehrm-full-width-grid']/div[2]//div[@class='oxd-select-text-input']")));
        dropdown.click();

        // Wait for dropdown options to appear
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                ////*[@id="app"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div[1]/div[1]/div/div[2]/div/div[2]
                By.xpath("//div[@role='listbox']//span")));

        // Select option by text
        for (WebElement option : options) {
            if (option.getText().equalsIgnoreCase(Text)) {
                option.click();
                System.out.println("Selected Category: " + Text);
                break;
            }
        }
    }

/*
    public void selectJobTitlee(String Text) throws InterruptedException {
        verifyElement.verifyBrowserElement(jobTitleDropDown, "jobTitleDropDown", 60);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Click dropdown
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='oxd-grid-3 orangehrm-full-width-grid']/div[4]//div[@class='oxd-select-text-input']")));
        dropdown.click();

        List<WebElement> options = wait.until(driver1 -> {
            List<WebElement> elems = driver1.findElements(By.xpath("//div[@role='listbox']//span"));
            for (WebElement e : elems) {
                if (e.getText().equalsIgnoreCase(Text)) {
                    return elems; // return list when text found
                    Thread.sleep(8000); // Temporary wait to ensure visibility
                    option.click();
                    System.out.println("Selected Job Title: " + Text);
                    break;
                }
            }
            return null; // keep waiting
        });
        // Wait for dropdown options to appear
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                ////*[@id="app"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div[1]/div[1]/div/div[2]/div/div[2]
                By.xpath("//div[@role='listbox']//span")));

        // Select option by text
        for (WebElement option : options) {
            if (option.getText().equalsIgnoreCase(Text)) {
                Thread.sleep(8000); // Temporary wait to ensure visibility
                option.click();
                System.out.println("Selected Job Title: " + Text);
                break;
            }
        }
    }
    */

    public void selectJobTitle(String optionText) {
        verifyElement.verifyBrowserElement(jobTitleDropDown, "jobCategoryDropDown", 60);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Wait for dropdown to be clickable
        wait.until(ExpectedConditions.elementToBeClickable(jobTitleDropDown));

        // Scroll dropdown into view and click via JS
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center', inline:'nearest'});",
                jobTitleDropDown
        );
        //((JavascriptExecutor) driver).executeScript("arguments[0].click();", jobTitleDropDown);
        jobTitleDropDown.click();

        // Build dynamic XPath for the option text
        String optionXpath = "//div[@role='listbox']//span[text()='" + optionText + "']";

        // Wait until the option is visible
        WebElement desiredOption = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXpath))
        );

        // Click the desired option via JS
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", desiredOption);

        System.out.println("Selected Job Title: " + optionText);
    }

/*
    public void seleectJobTitle(String jobTitle) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Scroll dropdown into view
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center', inline:'nearest'});",
                dropdown
        );

        // Click the dropdown via JS to avoid click interception
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdown);

        // Construct dynamic XPath using the option text
        String optionXpath = "//div[@role='listbox']//span[text()='" + optionText + "']";

        // Wait until the option is visible
        WebElement desiredOption = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(optionXpath)
        ));

        // Click via JS
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", desiredOption);

        System.out.println("Selected option: " + optionText);
            }
        }
    }

    public void selectJobTitle( String optionText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Scroll the dropdown into center of viewport
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center', inline:'nearest'});",
                dropdown
        );

        // Click the dropdown via JS to avoid click interception
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdown);

        // Wait for listbox to appear
        WebElement listbox = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@role='listbox']")
        ));

        // Wait dynamically for the option with desired text
        WebElement desiredOption = wait.until(driver1 -> {
            List<WebElement> options = driver1.findElements(By.xpath("//div[@role='listbox']//span"));
            for (WebElement option : options) {
                if (option.getText().equalsIgnoreCase(optionText)) {
                    return option;
                }
            }
            return null; // keep waiting
        });

        // Click the option
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", desiredOption);
        System.out.println("Selected: " + optionText);
    }

 */



    public void selectSubUnitDropDown(String Text) throws InterruptedException {
        verifyElement.verifyBrowserElement(subUnitDropDown, "subUnitDropDown", 60);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", subUnitDropDown);
        // Click dropdown
        Thread.sleep(6000); // Temporary wait to ensure visibility
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='oxd-grid-3 orangehrm-full-width-grid']/div[5]//div[@class='oxd-select-text-input']")));
        dropdown.click();

        // Wait for dropdown options to appear
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                ////*[@id="app"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div[1]/div[1]/div/div[2]/div/div[2]
                By.xpath("//div[@role='listbox']//span")));

        // Select option by text
        for (WebElement option : options) {
            if (option.getText().equalsIgnoreCase(Text)) {
                Thread.sleep(8000); // Temporary wait to ensure visibility
                option.click();
                System.out.println("Selected Sub Unit: " + Text);
                break;
            }
        }
    }

    public void selectLocationDropDown(String Text) throws InterruptedException {
        verifyElement.verifyBrowserElement(locationDropDown, "locationDropDown", 60);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Click dropdown
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='oxd-grid-3 orangehrm-full-width-grid']/div[6]//div[@class='oxd-select-text-input']")));
        dropdown.click();

        // Wait for dropdown options to appear
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                ////*[@id="app"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div[1]/div[1]/div/div[2]/div/div[2]
                By.xpath("//div[@role='listbox']//span")));

        // Select option by text
        for (WebElement option : options) {
            if (option.getText().equalsIgnoreCase(Text)) {
                Thread.sleep(8000); // Temporary wait to ensure visibility
                option.click();
                System.out.println("Selected Location: " + Text);
                break;
            }
        }
    }

    public void selectEmploymentStatus(String Text) {
        verifyElement.verifyBrowserElement(employmentStatusDropDown, "employmentStatusDropDown", 60);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Click dropdown
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='oxd-grid-3 orangehrm-full-width-grid']/div[7]//div[@class='oxd-select-text-input']")));
        dropdown.click();

        // Wait for dropdown options to appear
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                ////*[@id="app"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div[1]/div[1]/div/div[2]/div/div[2]
                By.xpath("//div[@role='listbox']//span")));

        // Select option by text
        for (WebElement option : options) {
            if (option.getText().equalsIgnoreCase(Text)) {
                option.click();
                System.out.println("Selected Employment: " + Text);
                break;
            }
        }
    }

    public void clickJobDetailsSaveBtn() {
        verifyElement.verifyBrowserElement(secSaveBtn, "Save Button successfully Validated", 60);
        secSaveBtn.click();
        ExtentTestManager.getTest().log(Status.PASS, secSaveBtn + " clicked successfully.");
        System.out.println(secSaveBtn + " successfully Entered on " + Device);

    }
}