•	Framework and Tools:
We have used Selenium 4 with JDK 21. TestNG framework has been used for annotations, grouping, prioritizing and reporting. Maven dependencies have been utilized as part of the framework.

Below are the guidelines for writing and organizing automated test scripts:

The framework has used Page-Object Model where for each webpage separate java class has been created. Inside src/main/java, a package named ‘PageObject’ has been created. Inside this package, class files are written for each webpage. Each file contains locators and corresponding methods to perform test.
Two more packages, named ‘AbstractComponent’ and ‘Resources’ packages are also created inside ‘src/main/java’. ‘AbstractComponent’ package contains class named ‘AbstractComponent.java’ which contains commonly used selenium methods, for example, WebDriverWait, Actions etc. ‘Resources’ package contains a class named ‘ExtentReporterNG’ which is responsible for configuring ExtentReport for test reporting purpose. The package contains a properties file named ‘GlobalData.properties’ which contains the global parameters such as browser name. We can use this file for storing the environment-specific URLs as well.
‘src/test/java’ contains three packages named – ‘TestCases’, ‘TestComponents’, ‘TestData’. The ‘TestCases’ package contains the testcase class files where each test-step has been written. ‘TestComponents’ package consists of two classes – ‘BaseTest.java’ and ‘Listeners.java’. ‘BaseTest.java’ is responsible for all the commonly used methods in each testcase like initializing the WebDriver, triggering the base URL, closing the browser after each test, code to capture and store the screenshot, code to read the data stored in JSON file. The Listeners.java’ is responsible for implementing the ITestListener abstract class where we create the unique instance for each testcase and log the status of each testcase.
‘TestData’ package contains the .json files where the test data are kept.
We have ‘reports’ folder under the project folder where the latest ‘index.html’ report is generated and stored.
Under the project folder, there is a folder named ‘testsuites’ where the TestNG.xml files are stored.
All maven dependencies are mentioned inside ‘pom.xml’ file.

