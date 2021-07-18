/**
 * 
 */
package com.restapi.restassured.utilities;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.apache.log4j.Logger;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * @author Shravani Maande
 *
 */
public class Listeners extends TestListenerAdapter {
	
	//public Logger logger;
	public ExtentSparkReporter htmlReporter;
	public ExtentReports extentReport;
	public ExtentTest  	test;
	
	/*
	 * Listeners(){ logger=Logger.getLogger(getClass().getSimpleName()); }
	 */
	public void onStart(ITestContext testContext) {
		htmlReporter=new ExtentSparkReporter(System.getProperty("user.dir")+"\\Reports\\restassuredapitest.html");
		System.out.println(htmlReporter+"is the htmlreporter");
		System.out.println("Entered into onstart method"+"The path is "+System.getProperty("user.dir"));
		htmlReporter.config().setDocumentTitle("RestAssuredAutomationReport");
		htmlReporter.config().setReportName("RestAPIEmployeeTest");
		htmlReporter.config().setTheme(Theme.DARK);
		
		extentReport=new ExtentReports();
		extentReport.attachReporter(htmlReporter);
		extentReport.setSystemInfo("hostname", "localhost");
		extentReport.setSystemInfo("Environment", "QA");
		extentReport.setSystemInfo("user", "praveen");
		System.out.println("all the lines executed");
	}
	
	public void onTestSuccess(ITestResult result) {
		test=extentReport.createTest(result.getName());
		test.log(Status.PASS,"Test case passed is :"+result.getName());
	}
	
	public void onTestFailure(ITestResult result) {
		test=extentReport.createTest(result.getName());
		test.log(Status.FAIL,"Test case failed is :"+result.getName());
	}
	
	public void onTestSkipped(ITestResult result) {
		test=extentReport.createTest(result.getName());
		test.log(Status.SKIP,"Test case skipped is :"+result.getName());
	}
	
	public void onTestFinish(ITestContext testContext) {
		extentReport.flush();
	}

}
