 package com.revature.pageobjectmodel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	
	@FindBy(id = "username")
	public WebElement usernameBox;
	@FindBy(id = "password")
	public WebElement passwordBox;
	@FindBy(className = "btn")
	public WebElement loginButton;
	
	public WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
		
	public void login(String username, String password) {				
		this.usernameBox.sendKeys(username);
		this.passwordBox.sendKeys(password); 
		this.loginButton.click();
	}
	

}