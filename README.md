# OrangeHRM-Selenium-Automation

OrangeHRM End-to-End Employee Management Test Automation
This project automates the Employee Management workflow on OrangeHRM Demo using Selenium, Java, TestNG, and ExtentReports, structured with the Page Object Model (POM).
Tech Stack
Component	Technology
Language	Java (v18+)
Automation Tool	Selenium WebDriver
Test Framework	TestNG
Reporting	ExtentReports
Design Pattern	Page Object Model (POM)
Environment Config	.env + ConfigReader.java
Test Data Source	Excel (Apache POI)
Dependency Manager	Maven
IDE	IntelliJ IDEA / Eclipse
 Environment Configuration
Create a .env file in your project root and include the following values:
BASE_URL=https://opensource-demo.orangehrmlive.com
username=Admin
password=admin123
BROWSER=edge
HEADLESS=false
EXCEL_FILE_PATH=src/test/resources/testdata/EmployeeData.xlsx
 Configuration Notes
BASE_URL → Application URL under test
username / password → Login credentials for OrangeHRM
BROWSER → Supported values: chrome, edge, firefox
HEADLESS → Set true for CI runs (no browser UI) or false for local debugging
EXCEL_FILE_PATH → Path to the employee data Excel file used for test input
Example Excel Columns:
FirstName | MiddleName | LastName | JobTitle | Nationality | MaritalStatus | DOB | Gender
The framework reads test data dynamically using the configured Excel file, allowing easy updates without modifying the test code.
 Setup Instructions
 Clone the Repository
git clone https://github.com/<your-github-username>/OrangeHRM_Automation.git
cd OrangeHRM_Automation
 Install Dependencies
Make sure Maven is installed, then run:
mvn clean install
 Update .env
Configure your browser and test data preferences in the .env file as shown above.
 Browser Configuration
The framework automatically reads the BROWSER variable from your .env and launches the correct driver.
Supported browsers:
 Chrome
 Edge
 Firefox
Example:
BROWSER=chrome
HEADLESS=true
Will execute tests in Chrome in headless mode.
 Run Tests
To execute the full Employee Management flow:
mvn test
Run specific TestNG suite (if using XML):
mvn test -DsuiteXmlFile=testng.xml
 Reports and Screenshots
After each test run:
HTML report → /test-output/ExtentReports/ExtentReport.html
Screenshots → /screenshots/
Console logs → Include device/browser context (e.g., Edge, Chrome)
 Highlights
Multi-browser execution via .env
 Excel-driven test data input
 Page Object Model for maintainability
 ExtentReports for rich visual reporting
 Configurable headless mode for CI/CD
 Centralized element utilities with ElementFunctionality
 Dynamic waits and error handling
Example Test Flow
Login → Dashboard
Navigate to PIM
Add new employee using Excel data
Fill personal and job details
Upload attachment
Save and validate popups
Search and verify employee in list
Capture final screenshot
👨‍💻 Author
Karabo Nkile
Automation Tester | Selenium | TestNG | Java | POM
