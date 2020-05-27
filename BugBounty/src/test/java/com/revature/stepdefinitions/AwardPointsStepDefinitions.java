package com.revature.stepdefinitions;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.revature.BugBountyApplication;
import com.revature.model.BugReport;
import com.revature.model.User;
import com.revature.pageobjectmodel.LoginPage;
import com.revature.service.BugReportService;
import com.revature.service.UserService;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class AwardPointsStepDefinitions extends AbstractTestNGSpringContextTests{
	
	@Autowired
	private BugReportService brs;
	@Autowired
	private UserService urs;
	
	private LoginPage login;
	private WebDriver driver;
	
	private User user;
	private BugReport bug;
	
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://localhost:4200/index");
		login = new LoginPage(driver);
		bug = brs.findById(2);
		user = urs.findUserByUsername("David");
		bug.setStatus("resolved");
		bug.setResolver(user);
		
	}
	
	@Given("<bug_report> is resolved")
	@Test
	public void bug_report_is_resolved() {
		//Assert that bug report resolved 
		WebDriverWait wait = new WebDriverWait(driver, 4);
		wait.until(ExpectedConditions.elementToBeClickable(login.loginButton));

		Assert.assertEquals("resolved", bug.getStatus());
	}

	@Given("<user> is the one who resolved this <bug_report>")
	@Test
	public void user_is_the_one_who_resolved_this_bug_report() {
	    // Assert that user resolved bug;
			
		Assert.assertEquals(user.getId(), bug.getResolver().getId());
	}

	@Then("<user> receives points")
	@Test
	public void user_receives_points() {
	    // Assert that the points of the user where added
	   Assert.assertTrue(user.getPoints() > 0);
	}
	
	@After
	public void tearDown() {
		this.driver.quit();
	}
}
