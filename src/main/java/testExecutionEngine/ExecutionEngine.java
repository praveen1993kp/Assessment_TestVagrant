package testExecutionEngine;

import base.GenericKeywords;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import reporter.ExtentReport;
import utils.CommonUtils;


public class ExecutionEngine {

	private static GenericKeywords genericKeywords = new GenericKeywords();
	
	/**
	  * Method To load properties file and create report for the reporter
	  */
	@BeforeSuite(alwaysRun = true)
	public void beforeSuiteResource(){
		genericKeywords.prop = new CommonUtils().readFromPropertiesFile("config");
		new ExtentReport().createReport();
	}

	
	/**
	  * Method to initialize and set WebDriver
	  */
	@BeforeMethod(alwaysRun = true)
	public void beforeMethodResource(){
		genericKeywords.initializeDriver();
		genericKeywords.setWebDriverWait();
	}

	/**
	  * Method to Close browser after method
	  */
	@AfterMethod(alwaysRun = true)
	public void closeBrowser() {
		genericKeywords.closeBrowser();
	}

	/**
	  * Method flush and close reporter
	  */
	@AfterSuite(alwaysRun = true)
	public void flushReport(){
		ExtentReport.report.flush();
	}



}