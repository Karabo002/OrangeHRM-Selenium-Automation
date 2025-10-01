package tests;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.*;
import webpages.*;
import com.aventstack.extentreports.Status;

import java.time.Duration;

public class EmployeeManagementTest extends BaseTest {

    @Test
    public void openBrowserAndLogin() {
        // Start the Extent test
        //ExtentTestManager.startTest("Login Test");
        ExtentTestManager.getTest().log(Status.PASS, "Login successful");

        try {
            String url = ConfigReader.get("BASE_URL");
            String username = ConfigReader.get("username");
            String password = ConfigReader.get("password");

            System.out.println("URL: " + url);
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);

            if (url == null || url.isEmpty()) Assert.fail("URL input must be set");
            if (username == null || username.isEmpty()) Assert.fail("Username input must be set");
            if (password == null || password.isEmpty()) Assert.fail("Password input must be set");


            driver.get(url);

            LoginPage loginPage = new LoginPage(driver );
            loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));

            DashboardPage dashboard = new DashboardPage(driver,"Employee Management");
            dashboard.dashboarderHeader();

            PIMPage pimPage = new PIMPage(driver,"Main Bar");
            pimPage.clickPimButton();

            AddEmployeePage addEmployeePage  = new AddEmployeePage (driver,"Employee Page");
            addEmployeePage.clickAddEmployeeBtn();
            addEmployeePage.enterFirstName("John");
            addEmployeePage.takeScreen_shot( "AddEmployeePage");
            addEmployeePage.enterMiddleName("A.");
            addEmployeePage.enterlastName("Doe");
            addEmployeePage.enterEmpoyeeId("12345");
            addEmployeePage.clickSaveBtn();
            addEmployeePage.verifyPopupMessage();
            addEmployeePage.takeScreen_shot( "verifyPopupMessage");
            addEmployeePage.selectNationality("South African");
            addEmployeePage.takeScreen_shot( "South African");
            addEmployeePage.selectMaritalStatus("Single");
            addEmployeePage.enterDateOfBirth("1990-01-01");
            addEmployeePage.clickMaleCheckBox();
            addEmployeePage.clickSecondarySaveBtn();
            addEmployeePage.verifyPopupMessageEmployeeAdded();
            addEmployeePage.clickAddAttachmentBtn();
            addEmployeePage.takeScreen_shot( "clickAddAttachmentBtn");
            //addEmployeePage.uploadAttachment("src/test/resources/testfiles/Book1.xlsx");

            JobDetailsPage jobDetailsPage = new JobDetailsPage(driver, "Job Details Page");
            jobDetailsPage.navigateToJobDetailsTab();
            jobDetailsPage.enterJoinedDate("2020-05-15");
            jobDetailsPage.selectJobTitle("QA Engineer");
            jobDetailsPage.selectJobCategory("Professionals");
            jobDetailsPage.selectSubUnitDropDown("Quality Assurance");
            jobDetailsPage.selectLocationDropDown( "New York Sales Office");
            jobDetailsPage.selectEmploymentStatus("Full-Time Contract");
            jobDetailsPage.clickJobDetailsSaveBtn();
            addEmployeePage.verifyPopupMessageEmployeeAdded();

            EmployeeListPage employeeListPage = new EmployeeListPage(driver, "Employee List Page");
            employeeListPage.clickemployeeListBtn();
            employeeListPage.searchEmployee("John", "Doe");





            // Take screenshot
            String screenshotPath = ScreenshotUtil.takeScreenshot(driver, "LoginResult");
            if (screenshotPath != null) {
                ExtentTestManager.getTest().log(Status.PASS, "Login successful.");
                ExtentTestManager.getTest().addScreenCaptureFromPath(screenshotPath);
            } else {
                ExtentTestManager.getTest().log(Status.FAIL, "Failed to take screenshot after login.");
            }

            ExtentTestManager.getTest().log(Status.PASS, "Login test completed successfully.");

        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.FAIL, "Test failed: " + e.getMessage());
            Assert.fail(e.getMessage()); // Mark TestNG test as failed
        }

    }
}