package webpages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.ElementFunctionality;
import utilities.ExtentTestManager;

import java.time.Duration;
import java.util.List;

public class EmployeeListPage {

        private WebDriver driver;
        public String Device;
        public ElementFunctionality verifyElement;
        public ElementFunctionality waitForPageLoad;

        @FindBy(xpath = "//a[.='Employee List']")
        private WebElement employeeListBtn;

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


        public EmployeeListPage(WebDriver driver, String Device) {
            this.driver = driver;
            this.Device = Device;
            verifyElement = new ElementFunctionality(driver, Device);
            PageFactory.initElements(driver, this);
        }

    public void clickemployeeListBtn() {
        verifyElement.verifyBrowserElement(employeeListBtn, " Employee List Button Successfully Validated", 60);
        employeeListBtn.click();
        System.out.println("Add Employee Button Clicked Successfully on " + Device);
        ExtentTestManager.getTest().log(Status.PASS, employeeListBtn + " clicked successfully.");
        System.out.println(employeeListBtn + " clicked successfully on " + Device);


    }

    public boolean searchEmployee(String employeeName, String employeeID) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Wait for the employee table
        WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='oxd-table orangehrm-employee-list']")
        ));

        // Get all rows
        List<WebElement> rows = table.findElements(By.xpath(".//div[@role='row']"));

        for (WebElement row : rows) {
            String nameText = "";
            String idText = "";

            try {
                // Adjust the column indexes as per table structure
                nameText = row.findElement(By.xpath(".//div[@role='cell'][2]")).getText().trim();
                idText = row.findElement(By.xpath(".//div[@role='cell'][1]")).getText().trim();
            } catch (NoSuchElementException e) {
                continue; // skip header or empty rows
            }

            // Match by name or ID
            if (nameText.equalsIgnoreCase(employeeName) || idText.equals(employeeID)) {
                System.out.println("Employee found: " + nameText + " | ID: " + idText);
                return true;
            }
        }

        System.out.println("Employee not found: " + employeeName + " | ID: " + employeeID);
        return false;
    }

}