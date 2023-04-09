package com.Framework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {
	
	public static ExtentReports generateExtentReport() {
	
	ExtentReports extentReport = new ExtentReports();
	
	File reportFile = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReport\\ExtentReport.html");
	ExtentSparkReporter spark = new ExtentSparkReporter(reportFile);
	
	spark.config().setTheme(Theme.DARK);
	spark.config().setReportName("Tutorials Ninja Automation Testing");
	spark.config().setDocumentTitle("Automation Testing Report");
	spark.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
	
	extentReport.attachReporter(spark);
	
	Properties prop = new Properties();
	try {
		FileInputStream file = new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\TutorialsNinja\\qa\\config\\Config.properties"));
		prop.load(file);
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	extentReport.setSystemInfo("Application URL", prop.getProperty("url"));
	extentReport.setSystemInfo("Browser:", prop.getProperty("browserName"));
	extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
	extentReport.setSystemInfo("User Name", System.getProperty("user.name"));
	extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
	
	return extentReport;

	
	}
}
