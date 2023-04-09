package com.tutorialsNinjaTestcases;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Framework.utils.Utilities;
import com.TutorialsNinja.qa.pages.AccountPage;
import com.TutorialsNinja.qa.pages.HomePage;
import com.TutorialsNinja.qa.pages.LoginPage;
import com.tutorialNinja.Base.BaseClass;

public class LoginTest extends BaseClass {
	
	public  WebDriver driver;
	LoginPage login;
	
	public  LoginTest(){
		super();
	}
	
	@BeforeMethod
	public void setup() {
		
		driver = initializeBrowserAndApplication(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		homePage.clickMyAccountDropdown();
		login = homePage.selectLoginOption();	
	}
	
	@AfterMethod
	public void tearDown() throws InterruptedException {
		driver.quit();
		Thread.sleep(3000);
	}
	
	@Test(priority=1,dataProvider = "Supply1")
	public void verifyLoginWithValidCredentials(String email,String passwrod) {
		
		
		login.enterEmailAddress(email);
		login.enterPassword(passwrod);
		login.clickLoginButton();
		
		AccountPage accountPage = new AccountPage(driver);
		 Assert.assertTrue(accountPage.displayStatusOfEditYourAccountInfo());
		 
	}
	
	@DataProvider(name="Supply1")
	public Object[][] supplyData(){
		Object[][] data=Utilities.getDataFromExcel("Sheet1");
		return data;
	}
	

	
	
	@Test(priority=2)
	public void verifyLoginWithInvalidCredentials() {
		
		
		login.enterEmailAddress(prop.getProperty("validEmail"));
		login.enterPassword(dataProp.getProperty("invalidPassword"));
		login.clickLoginButton();
		
	}
	
	
}
