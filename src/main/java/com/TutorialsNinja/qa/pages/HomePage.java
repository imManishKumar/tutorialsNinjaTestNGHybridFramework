package com.TutorialsNinja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
	
	@FindBy(xpath = "//span[text()='My Account']")
	private WebElement myAccountDropdown;
	
	@FindBy(linkText = "Login")
	private WebElement loginOption;
	

	@FindBy(linkText = "Register")
	private WebElement registerOption;
	
	@FindBy(name="search")
	private WebElement searchbox;
	
	@FindBy(xpath="//div[@id='search']/span/button")
	private WebElement searchbutton;
	
	public HomePage(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public void clickSearchButton() {
		searchbutton.click();
	}
	
	public void enterProductNameToSearch(String product) {
		searchbox.sendKeys(product);
	}
	
	public RegisterPage clickRegister() {
		registerOption.click();
		return new RegisterPage(driver);
	}
	
	public void clickMyAccountDropdown() {
		myAccountDropdown.click();
	}
	
	public LoginPage selectLoginOption() {
		loginOption.click();
		return new LoginPage(driver);
	}

}
