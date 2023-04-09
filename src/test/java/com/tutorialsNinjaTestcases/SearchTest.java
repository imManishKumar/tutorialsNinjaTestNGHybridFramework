package com.tutorialsNinjaTestcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.TutorialsNinja.qa.pages.HomePage;
import com.tutorialNinja.Base.BaseClass;

public class SearchTest extends BaseClass {
	
	public WebDriver driver;
	
	public SearchTest(){
		super();
	}
	
	@BeforeMethod
	public void setup(){
		
		driver=initializeBrowserAndApplication(prop.getProperty("browserName"));
		
	}
	
	@AfterMethod
	public void tearDown() throws InterruptedException {
		
		driver.quit();
		Thread.sleep(3000);
	}
	@Test(priority=1)
	public void verifySearchWithValidProduct() {
		HomePage homePage = new HomePage(driver);
		homePage.enterProductNameToSearch(dataProp.getProperty("validProduct"));
		homePage.clickSearchButton();
		
		Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed(), "Valid Product HP is not displayed");
		
	}
	@Test(priority=2) 
	public void verifySearchWithInvalidProduct() {
		HomePage homePage = new HomePage(driver);
		homePage.enterProductNameToSearch(dataProp.getProperty("invalidProduct"));
		homePage.clickSearchButton();
		String actualMessage = driver.findElement(By.xpath("//div[@id='content']//input[@type='button']//following-sibling::p")).getText();
		Assert.assertEquals(actualMessage, dataProp.getProperty("noProductText"),"'No Product' message is not displayed");
		
		
		
	}
	
	@Test(priority=3,dependsOnMethods = "verifySearchWithInvalidProduct") 
	public void verifySearchWithoutAnyProduct() {
		HomePage homePage = new HomePage(driver);
		driver.findElement(By.name("search")).sendKeys("");
		homePage.clickSearchButton();
		String actualMessage = driver.findElement(By.xpath("//div[@id='content']//input[@type='button']//following-sibling::p")).getText();
		Assert.assertEquals(actualMessage, dataProp.getProperty("noProductText"),"'No Product' message is not displayed");
		
		
		
	}

}
