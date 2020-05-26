package com.revature.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.pageobjectmodel.LoginPage;
import com.revature.pageobjectmodel.Navbar;

public class test {

	private WebDriver driver;
	private final String URL = "localhost:4200/";
	private LoginPage loginPage;
	private Navbar navbar;

	@BeforeClass
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(URL);
		
		loginPage = new LoginPage(driver);
	}
	
	@Test
	public void testLogin() {
		loginPage.login("user", "pass");
		Assert.assertEquals(this.driver.getCurrentUrl(), "localhost:4200/home");
	}
	
	@Test
	public void testNavbar() {
		loginPage.login("user", "pass");
		navbar = new Navbar(driver);
		navbar.clickPendingBug();
		Assert.assertEquals(this.loginPage.driver.getCurrentUrl(), "localhost:4200/pendingbug");
	}
	
	@AfterClass
	public void tearDown() {
		//This is here so that we can actually see what happens when the script
		//is running as it tends to move pretty fast.
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//Close the browser.
		this.loginPage.driver.quit();
	}
	
}