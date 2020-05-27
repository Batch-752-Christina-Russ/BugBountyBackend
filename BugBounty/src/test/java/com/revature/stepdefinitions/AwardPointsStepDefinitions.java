package com.revature.stepdefinitions;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.revature.model.BugReport;
import com.revature.pageobjectmodel.LoginPage;
import com.revature.pageobjectmodel.Navbar;
import com.revature.pageobjectmodel.ResolveBugPage;
import com.revature.service.BugReportService;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class AwardPointsStepDefinitions{
	
	@Autowired
	private BugReportService brs;
	
	private ResolveBugPage resolve;
	private LoginPage login;
	private Navbar nb;
	private WebDriver driver;
	
	private BugReport bug;
	
	@Before
	public void setUp() {
		
	}
	
	@Given("<bug_report> is resolved")
	@Test
	public void bug_report_is_resolved() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://localhost:4200/index");
		login = new LoginPage(driver);
		nb = new Navbar(driver);
		resolve = new ResolveBugPage(driver);
		driver.manage().window().maximize();
		
		WebDriverWait wait = new WebDriverWait(driver, 2);
		this.login.login("Admin", "pass");
		wait.until(ExpectedConditions.urlContains("home"));
		this.nb.clickResolveNav();
		wait.until(ExpectedConditions.urlContains("resolvebug"));
		this.resolve.resolveBug("2", "Stephanie");
		this.nb.clickLogoutNav();

		//Assert that bug report resolved 
		wait.until(ExpectedConditions.elementToBeClickable(login.loginButton));
		bug=brs.findById(2);
		Assert.assertEquals("resolved", bug.getStatus());
	}

	@Given("<user> is the one who resolved this <bug_report>")
	@Test
	public void user_is_the_one_who_resolved_this_bug_report() {
	    // Assert that user resolved bug;
		Assert.assertEquals("Stephanie", bug.getResolver().getUsername());
		
		// Login to see if individual points are updated
		this.login.login("Stephanie", "pass");
	}

	@Then("<user> receives points")
	@Test
	public void user_receives_points() {
		WebDriverWait wait = new WebDriverWait(driver, 2);
		wait.until(ExpectedConditions.urlContains("home"));
	   // Assert that the points of the user where added with whats displayed on the homepage
	   String points = this.driver
			            .findElement(By.xpath("//*[@id=\"floatboard\"]/app-user-rank/div[2]/table/tbody/tr/td[3]"))
			   			.getText();
	   Assert.assertTrue(Integer.parseInt(points)>0);
	   
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//Close the browser.
		this.driver.quit();
	}
	
	@AfterClass
	public void tearDown() {

	}
}
