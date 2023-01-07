# TestVagrant_Assessment - Selenium-Java Automation Framework for TestVagrant Round 1 Assessment

# Dependencies Used:
1. Selenium WebDriver    - 4.6.0    -- For automating Browser UI scenarios
2. TestNG                - 7.4.0    -- The Test Runner to execute Suite
3. Extent Reports        - 2.40.1   -- The HTML Reporting Library
4. Maven Compiler Plugin - 3.8.1    -- To Compile before running as Maven Test
5. Maven Surefire Plugin - 3.0.0    -- To Run as Maven Test


# Design Patterns Used:
1. Factory Design Pattern:
	* For driver initialization based on different browser name as input, Factory Design pattern is used

2. Adapter Design Pattern:
	* For configuring any new report at later time, interface is provided
	* This interface will help to configure new reports like Allure,etc., without affecting existing reporting setup

# Some of the Best Coding Practices followed:
1. Appropriate Naming Conventions
2. Usage of proper Intendation 
3. Usage of DRY (Don't Repeat Yourself) Principle
4. Avoiding Deep Nesting
5. Avoiding longer lines of code in each file
6. Proper exception handling
7. Provide comments for each step


# How to design your new test ?
* Step 1 : Use the main source for your framework design and test source for project specific configuration and tests
* Step 2 : Use base/GenericKeywords class to add Project specific reusable methods
* Step 3 : Use base/SeleniumGenericKeywords and base/NonSeleniumGenericKeywords interfaces to add abstract methods of GenericKeywords
* Step 4 : Use reporter/ReporterInterface for reporter specific abstract methods
* Step 5 : Use reporter/ExtentReportListener for extent report specific method definitions
* Step 6 : Use testExecutionEngine/ExecutionEngine for defining and configuring before/after methods
* Step 7 : Use utils/CommonUtils class to configure Framework specific generic methods
* Step 8 : Use src/test/pageObjects/ to have locators specific to each page available in pages/
* Step 9 : Use src/test/pages to configure page specific methods
* Step 10: Use src/test/testcases/ to define sequence of testSteps and perform assertions
* Step 11: Use src/test/resources/config.properties for configuring dynamic parameters like urls, movieName
* Step 12: For reports, after a run is completed, navigate to test-output


# Ways to Run Test:
* Approach 1 : Use Run Suite Option from testng.xml
* Approach 2 : Use Run As/TestNG Suite
* Approach 3 : Use "Run All" option available in testcases/TC01_Assertion.java
* Approach 4 : Using command line, Navigate to project directory(./TestVagrant_Assessment) and execute "mvn clean test"
* Approach 5 : Use Remote Jenkins URL to trigger (In Progress since it requires a Server to be executed)


# Current Build Status:
* The build is configured to run for single/multiple movies


# How to configure new movie:
* Step 1 : For configuring a new movie - Navigate to /test/resources and provide new values with ";;;" as delimiter
* Step 2 : Run the test using any of the above approaches
* Note   : The tests for each movie will run one after other and assertion will take at each step


# How to remove existing movie:
* Step 1 : For removing an existing movie, Navigate to /test/resources and remove movie alone with preceeding delimiter (;;;)
* Step 2 : Run the test using any of the above approaches



