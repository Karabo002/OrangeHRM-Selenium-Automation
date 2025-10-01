package webpages;

import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.w3c.dom.Text;
import utilities.ElementFunctionality;
import utilities.ExtentTestManager;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class AddEmployeePage {


    private WebDriver driver;
    public String Device;
    public ElementFunctionality verifyElement;
    public ElementFunctionality waitForPageLoad;

    @FindBy(xpath = "//a[.='Add Employee']")
    private WebElement addEmployeeBtn;

    @FindBy(xpath = "//input[@name='firstName']")
    private WebElement firstName;

    @FindBy(xpath = "//input[@name='middleName']")
    private WebElement middleName;

    @FindBy(xpath = "//input[@name='lastName']")
    private WebElement lastName;

    @FindBy(xpath = "//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']")
    private WebElement saveBtn;

    @FindBy(xpath = " //div[@class='oxd-grid-2 orangehrm-full-width-grid']//input[@class='oxd-input oxd-input--active']")
    private WebElement empoyeeId;

    @FindBy(xpath = " //div[@class='oxd-toast-content oxd-toast-content--success']")
    private WebElement popupMessage;
    ;

    @FindBy(xpath = "//div[@class='orangehrm-edit-employee-content']/div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']//div[@class='oxd-grid-3 orangehrm-full-width-grid']/div[1]//div[@class='oxd-select-text-input']")
    private WebElement nationalityDropDown;

    @FindBy(xpath = "//div[@class='orangehrm-edit-employee-content']//div[@class='oxd-grid-3 orangehrm-full-width-grid']/div[1]//div[@class='oxd-date-wrapper']//input[@class='oxd-input oxd-input--active']")
    private WebElement dateOfBirthInput;

    @FindBy(xpath = "//div[@class='orangehrm-edit-employee-content']/div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']")
    private WebElement secondtarySaveBtn;

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


    public AddEmployeePage(WebDriver driver, String Device) {
        this.driver = driver;
        this.Device = Device;
        verifyElement = new ElementFunctionality(driver, Device);
        PageFactory.initElements(driver, this);
    }

    public void clickAddEmployeeBtn() {
        verifyElement.verifyBrowserElement(addEmployeeBtn, "Add Employee Button Successfully Validated", 60);
        addEmployeeBtn.click();
        System.out.println("Add Employee Button Clicked Successfully on " + Device);
        ExtentTestManager.getTest().log(Status.PASS, addEmployeeBtn + " clicked successfully.");
        System.out.println(addEmployeeBtn + " clicked successfully on " + Device);

    }

    public void enterFirstName(String Text) {
        verifyElement.verifyBrowserElement(firstName, "First Name successfully Validated", 60);
        firstName.sendKeys(Text);
        ExtentTestManager.getTest().log(Status.PASS, firstName + " clicked successfully.");
        System.out.println(firstName + "  successfully Entered on " + Device);

    }

    public void enterMiddleName(String Text) {
        verifyElement.verifyBrowserElement(middleName, "Middle Name successfully Validated", 60);
        middleName.sendKeys(Text);
        ExtentTestManager.getTest().log(Status.PASS, middleName + " clicked successfully.");
        System.out.println(middleName + " successfully Entered on " + Device);

    }

    public void enterlastName(String Text) {
        verifyElement.verifyBrowserElement(lastName, "last Name successfully Validated", 60);
        lastName.sendKeys(Text);
        ExtentTestManager.getTest().log(Status.PASS, lastName + " clicked successfully.");
        System.out.println(lastName + " successfully Entered on " + Device);

    }

    public void enterEmpoyeeId(String Text) {
        verifyElement.verifyBrowserElement(empoyeeId, "Employee ID successfully Validated", 60);
        empoyeeId.clear();
        empoyeeId.sendKeys(Text);
        ExtentTestManager.getTest().log(Status.PASS, empoyeeId + " clicked successfully.");
        System.out.println(empoyeeId + " successfully Entered on " + Device);

    }

    public void clickSaveBtn() {
        verifyElement.verifyBrowserElement(saveBtn, "Save Button successfully Validated", 60);
        saveBtn.click();
        ExtentTestManager.getTest().log(Status.PASS, saveBtn + " clicked successfully.");
        System.out.println(saveBtn + " successfully Entered on " + Device);

    }

    public void verifyPopupMessage() {
        String elementName = "Employee Added Successfully Popup Message";
        try {
            verifyElement.verifyBrowserElement(popupMessage, elementName, 60);
            String screenshotPath = takeScreenshot("popup_message", popupMessage);
            ExtentTestManager.getTest().log(Status.PASS, elementName + " is visible.");
            System.out.println(elementName + " is visible on " + Device);

        } catch (Exception e) {
            String message = elementName + " could not be verified: " + e.getMessage();
            System.err.println(message);
            ExtentTestManager.getTest().log(Status.FAIL, message);
            Assert.fail(message);
        }
    }


    public void selectNationality(String nationality) throws InterruptedException {
        verifyElement.verifyBrowserElement(nationalityDropDown, "selectNationality", 60);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//div[@class='oxd-loading-spinner']")
        ));

        // Click dropdown
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='orangehrm-edit-employee-content']/div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']//div[@class='oxd-grid-3 orangehrm-full-width-grid']/div[1]//div[@class='oxd-select-text-input']")));
        dropdown.click();

        // Wait for dropdown options to appear
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                ////*[@id="app"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div[1]/div[1]/div/div[2]/div/div[2]
                By.xpath("//div[@role='listbox']//span")));

        // Select option by text
        for (WebElement option : options) {
            if (option.getText().equalsIgnoreCase(nationality)) {
                Thread.sleep(4000);
                option.click();
                System.out.println("Selected nationality: " + nationality);
                break;
            }
        }
    }



    public void sselectNationality(String nationality) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Wait for dropdown to be visible
        verifyElement.verifyBrowserElement(nationalityDropDown, "Nationality Dropdown", 60);

        // Scroll to center
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", nationalityDropDown);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nationalityDropDown);


        // Wait until clickable
        wait.until(ExpectedConditions.elementToBeClickable(nationalityDropDown));

        Thread.sleep(8000);
        // Click via JS to bypass overlay
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nationalityDropDown);

        // Wait for options
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("//div[@role='listbox']//span")
        ));

        // Select the option
        for (WebElement option : options) {
            if (option.getText().equalsIgnoreCase(nationality)) {
                Thread.sleep(500); // small pause
                option.click();
                System.out.println("Selected nationality: " + nationality);
                break;
            }
        }
    }



    public void selectMaritalStatus(String martialStatus) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Click dropdown
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='orangehrm-edit-employee-content']/div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']//div[@class='oxd-select-text oxd-select-text--active']/div[.='-- Select --']")));
        dropdown.click();

        // Wait for dropdown options to appear
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                ////*[@id="app"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div[1]/div[1]/div/div[2]/div/div[2]
                By.xpath("//div[@role='listbox']//div[@role='option']//span")));

        // Select option by text
        for (WebElement option : options) {
            if (option.getText().equalsIgnoreCase(martialStatus)) {
                option.click();
                Thread.sleep(4000);

                System.out.println("Selected nationality: " + martialStatus);
                break;
            }
        }
    }

    public void selectMaritalStatus(By dropdownLocator, String optionText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // 1️⃣ Click dropdown trigger
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(dropdownLocator));
            dropdown.click();

            // 2️⃣ Wait for options to appear
            List<WebElement> options = wait.until(ExpectedConditions
                    //
                    .visibilityOfAllElementsLocatedBy(By.xpath("//div[@role='listbox']//div[@role='option']//span")));

            // 3️⃣ Select the matching option
            boolean found = false;
            for (WebElement option : options) {
                if (option.getText().trim().equalsIgnoreCase(optionText)) {
                    option.click();
                    found = true;
                    System.out.println(" Selected: " + optionText);
                    break;
                }
            }

            if (!found) {
                System.err.println(" Option not found: " + optionText);
            }

        } catch (TimeoutException e) {
            System.err.println(" Dropdown did not appear in time: " + e.getMessage());
        }
    }


    public void enterDateOfBirth(String date) {
        try {
            verifyElement.verifyBrowserElement(dateOfBirthInput, "Date of Birth field", 30);
            dateOfBirthInput.clear();
            dateOfBirthInput.sendKeys(date);
            ExtentTestManager.getTest().log(Status.PASS, "Entered Date of Birth: " + date);
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.FAIL, "Failed to enter Date of Birth: " + e.getMessage());
            Assert.fail("Date of Birth entry failed");
        }
    }

    public void clickMaleCheckBox() {
        verifyElement.verifyBrowserElement(maleCheckBox, "Save Button successfully Validated", 60);
        maleCheckBox.click();
        ExtentTestManager.getTest().log(Status.PASS, maleCheckBox + " clicked successfully.");
        System.out.println(maleCheckBox + " successfully Entered on " + Device);

    }

    public void clickSecondarySaveBtn() {
        verifyElement.verifyBrowserElement(secondtarySaveBtn, "Secondary Save Button successfully Validated", 60);
        secondtarySaveBtn.click();
        ExtentTestManager.getTest().log(Status.PASS, saveBtn + " clicked successfully.");
        System.out.println(saveBtn + " successfully Entered on " + Device);

    }

    public void clickAddAttachmentBtn() {
        verifyElement.verifyBrowserElement(addAttachmentBtn, "Secondary Add Button successfully Validated", 60);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", addAttachmentBtn);

        // Move to the element using Actions (helps for hidden/styled inputs)
        Actions actions = new Actions(driver);
        actions.moveToElement(addAttachmentBtn).perform();
        addAttachmentBtn.click();
        ExtentTestManager.getTest().log(Status.PASS, addAttachmentBtn + " clicked successfully.");
        System.out.println(addAttachmentBtn + " successfully Entered on " + Device);

    }

    public void verifyPopupMessageEmployeeAdded() {
        String elementName = "Employee Added Successfully Popup Message";
        try {
            verifyElement.verifyBrowserElement(popupMessageEmployeeAdded, elementName, 60);
            String screenshotPath = takeScreenshot("popup_message", popupMessageEmployeeAdded);
            ExtentTestManager.getTest().log(Status.PASS, elementName + " is visible.");
            System.out.println(elementName + " is visible on " + Device);

        } catch (Exception e) {
            String message = elementName + " could not be verified: " + e.getMessage();
            System.err.println(message);
            ExtentTestManager.getTest().log(Status.FAIL, message);
            Assert.fail(message);
        }
    }

    public void uploadAttachment(String filePath) {
        try {
            verifyElement.verifyBrowserElement(fileUploadInput, "fileUploadInput", 60);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='file']")));

            // Make sure file input is visible
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].style.display='block'; arguments[0].removeAttribute('hidden');", fileUploadInput);

            // Move to file input (optional visual step)
            Actions actions = new Actions(driver);
            actions.moveToElement(fileUploadInput).perform();

            // Send file path
            fileUploadInput.sendKeys(filePath);
            ExtentTestManager.getTest().log(Status.PASS, "File selected: " + filePath);

            // Click Save/Upload button
            wait.until(ExpectedConditions.elementToBeClickable(savefileUploadBtn));
            savefileUploadBtn.click();
            ExtentTestManager.getTest().log(Status.PASS, "Clicked Upload button");

            // Verify file appears in attachment table
            wait.until(ExpectedConditions.visibilityOf(attachmentsTable));
            boolean fileFound = attachmentsTable.getText().contains(new File(filePath).getName());

            if (fileFound) {
                ExtentTestManager.getTest().log(Status.PASS, "✅ File uploaded successfully: " + filePath);
                System.out.println("✅ File uploaded successfully: " + filePath);
            } else {
                ExtentTestManager.getTest().log(Status.FAIL, "❌ File not found in attachments table: " + filePath);
                Assert.fail("File not found after upload: " + filePath);
            }

        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.FAIL, "Upload failed: " + e.getMessage());
            Assert.fail("Upload failed: " + e.getMessage());
        }
    }

    /*
    public void uploadAttachment(String filePath) {
        try {
            verifyElement.verifyBrowserElement(fileUploadInput, "fileUploadInput", 60);
            // Wait for file input to be clickable
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(fileUploadInput));

            javascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].style.display='block';", fileUploadInput);

// Move to the element using Actions (helps for hidden/styled inputs)
            Actions actions = new Actions(driver);
            actions.moveToElement(fileUploadInput).perform();

            fileUploadInput.click();

            // Send file path to input
            fileUploadInput.sendKeys(filePath);
            ExtentTestManager.getTest().log(Status.PASS, "File selected: " + filePath);

            // Click Upload button
            wait.until(ExpectedConditions.elementToBeClickable(savefileUploadBtn));
            savefileUploadBtn.click();
            ExtentTestManager.getTest().log(Status.PASS, "Clicked Upload button");

            // Verify file appears in attachments table
            wait.until(ExpectedConditions.visibilityOf(attachmentsTable));
            boolean fileFound = attachmentsTable.getText().contains(new File(filePath).getName());

            if(fileFound) {
                ExtentTestManager.getTest().log(Status.PASS, "File uploaded successfully: " + filePath);
                System.out.println("✅ File uploaded successfully: " + filePath);
            } else {
                ExtentTestManager.getTest().log(Status.FAIL, "File not found in attachments table: " + filePath);
                Assert.fail("File not found after upload: " + filePath);
            }

        } catch(Exception e) {
            ExtentTestManager.getTest().log(Status.FAIL, "Upload failed: " + e.getMessage());
            Assert.fail("Upload failed: " + e.getMessage());
        }
    }

     */


    public String takeScreen_shot(String fileName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);

            String destDir = "test-output/screenshots/";
            File dir = new File(destDir);
            if (!dir.exists()) dir.mkdirs();

            String dest = destDir + fileName + ".png";
            FileUtils.copyFile(src, new File(dest));
            return dest;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public String takeScreenshot(String fileName, WebElement elementToWaitFor) {
        try {
            // Wait for element to be visible (dynamic wait)
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(elementToWaitFor));

            // Take screenshot
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);

            // Prepare destination folder
            String destDir = "test-output/screenshots/";
            File dir = new File(destDir);
            if (!dir.exists()) dir.mkdirs();

            // Save file
            String dest = destDir + fileName + ".png";
            FileUtils.copyFile(src, new File(dest));

            return dest;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (TimeoutException e) {
            System.err.println("Element not visible in time, screenshot may not capture desired state.");
            return null;
        }
    }
}