package com.TutorialsNinja.qa.Listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.Framework.utils.ExtentReport;
import com.Framework.utils.Utilities;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class MyListeners implements ITestListener {
	ExtentReports extentReports;
	ExtentTest extentTest;

	@Override
	public void onStart(ITestContext context) {
		
		 extentReports = ExtentReport.generateExtentReport();
		
	}
	@Override
	public void onTestStart(ITestResult result) {
		extentTest = extentReports.createTest(result.getName());
		extentTest.log(Status.INFO, result.getName()+" test started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.log(Status.PASS, result.getName()+" test completed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		WebDriver driver=null;
		try{
			 driver =(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		String destinationScreenshotFolder = Utilities.captureScreenshot(driver, result.getName());
		extentTest.addScreenCaptureFromPath(destinationScreenshotFolder);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, result.getName()+" test failed");
	}
	

	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, result.getName()+" test skipped");
		
		
	}

	

	@Override
	public void onFinish(ITestContext context) {
		extentReports.flush();
		String reportPath = System.getProperty("user.dir")+"\\test-output\\ExtentReport\\ExtentReport.html";
		File reportFile = new File(reportPath);
		try {
			Desktop.getDesktop().browse(reportFile.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
