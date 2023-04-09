package com.tutorialNinja.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.Framework.utils.Utilities;

public class BaseClass {
	
	WebDriver driver;
	public Properties prop;
	public Properties dataProp;
	
	public BaseClass() {
		 prop = new Properties();
		File propFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\TutorialsNinja\\qa\\config\\Config.properties");
		dataProp= new Properties();
		File dataFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\TutorialsNinja\\qa\\testdata\\testdata.properties");
		FileInputStream testDataFile;
		try {
			testDataFile = new FileInputStream(dataFile);
			dataProp.load(testDataFile);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
				
		try {
		FileInputStream input = new FileInputStream(propFile);
		prop.load(input);
		}catch(Exception e) {
			e.printStackTrace();
		}
	} 
	
	public WebDriver initializeBrowserAndApplication(String browserName) {
		
		if(browserName.equals("chrome")) {
			driver = new ChromeDriver();
		}
		else if(browserName.equals("edge")) {
			driver = new EdgeDriver();
		}
		else if(browserName.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		 
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_WAIT));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		
		return driver;
	}

}
