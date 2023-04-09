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
import org.testng.annotations.Test;

import com.Framework.utils.Utilities;
import com.TutorialsNinja.qa.pages.HomePage;
import com.TutorialsNinja.qa.pages.RegisterPage;
import com.tutorialNinja.Base.BaseClass;

public class RegisterTest extends BaseClass {
	public WebDriver driver;
	RegisterPage registerPage;
	
	 public RegisterTest(){
		 super();
	 }
	
	@BeforeMethod
	public void setup() {
		
		driver = initializeBrowserAndApplication(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		homePage.clickMyAccountDropdown();
		registerPage = homePage.clickRegister();
	}
	
	@AfterMethod
	public void tearDown() throws InterruptedException{
		driver.quit();
		Thread.sleep(3000);
	}

	@Test(priority=1)
	public void verifyRegisteringAccountWithMandotoryFields() throws InterruptedException {
		
		
		
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmail(Utilities.generateEmailTimeStamp());
		registerPage.enterTelephoneNo(dataProp.getProperty("validTelephoneNo"));
		registerPage.enterpassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.clickPrivacyPolicyCheckbox();
		registerPage.clickContinueButton();
		Thread.sleep(3000);
		
		String successHeading = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		Assert.assertEquals(successHeading, dataProp.getProperty("accountCreatedSuccessfull"),"Account creation failed!");
		
		
	}
	
	@Test(priority=2)
	public void verifyRegisteringAccountByProvidingAllFields() {
	
		
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmail(Utilities.generateEmailTimeStamp());
		registerPage.enterTelephoneNo(dataProp.getProperty("validTelephoneNo"));
		registerPage.enterpassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		registerPage.clickPrivacyPolicyCheckbox();
		registerPage.clickContinueButton();
		
		
		String successHeading = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		Assert.assertEquals(successHeading, dataProp.getProperty("accountCreatedSuccessfull"),"Account creation failed!");
		
	}
	
	@Test(priority=3)
	public void verifyRegisteringWithExistingEmailAddress() {
		
		driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("lastName"));
		driver.findElement(By.id("input-email")).sendKeys("abc@gmail.com");
		driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("validTelephoneNo"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String actualWarning = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		Assert.assertTrue(actualWarning.contains(dataProp.getProperty("emailAddressAlreadyExists")), "Warning message for duplicate address is not displayed.");
		
	}
	
	@Test(priority=4)
	public void verifyRegisteringWithEmptyFields() {
	
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String actualPrivacyWarning = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
		Assert.assertTrue(actualPrivacyWarning.contains(dataProp.getProperty("privacyPolicyWarning")), "Warning message for not agreeing Privacy Policy.");
		
		String actualFirstnameWarning = driver.findElement(By.xpath("//input[@name='firstname']//following-sibling::div")).getText();
		Assert.assertTrue(actualFirstnameWarning.contains(dataProp.getProperty("firstNameWarning")), "Warning message for not entering First Name.");
		
		String actualLastnameWarning = driver.findElement(By.xpath("//input[@name='lastname']//following-sibling::div")).getText();
		Assert.assertTrue(actualLastnameWarning.contains(dataProp.getProperty("lastNameWarning")), "Warning message for not entering last Name.");
		
		String actualEmailWarning = driver.findElement(By.xpath("//input[@name='email']//following-sibling::div")).getText();
		Assert.assertTrue(actualEmailWarning.contains(dataProp.getProperty("emailAddressWarning")), "Warning message for not entering Email.");
		
		String actualPhoneWarning = driver.findElement(By.xpath("//input[@name='telephone']//following-sibling::div")).getText();
		Assert.assertTrue(actualPhoneWarning.contains(dataProp.getProperty("telephoneWarning")), "Warning message for not entering Phone number.");
		
		String actualPasswordWarning = driver.findElement(By.xpath("//input[@name='password']//following-sibling::div")).getText();
		Assert.assertTrue(actualPasswordWarning.contains(dataProp.getProperty("passwordWarning")), "Warning message for not entering Password.");
	}
	
	

}
