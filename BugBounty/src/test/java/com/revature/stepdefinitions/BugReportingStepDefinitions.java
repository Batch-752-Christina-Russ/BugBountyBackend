package com.revature.stepdefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.model.BugReport;
import com.revature.pageobjectmodel.HomePage;
import com.revature.pageobjectmodel.LoginPage;
import com.revature.pageobjectmodel.Navbar;
import com.revature.pageobjectmodel.ReportBugPage;
import com.revature.service.BugReportService;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class BugReportingStepDefinitions extends AbstractTestNGSpringContextTests{

	
	private final String URL = "http://localhost:4200/index";
	private WebDriver driver;
	private LoginPage lp;
	private ReportBugPage rbp;
	private Navbar nb;
	private HomePage hp;
	
	@Autowired
	private BugReportService bugReportService;

	@Before
	public void setUp() {	
		
	}
	
	@Given("the user is in the Bug Report Page")
	public void testLoginAndNavigate() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(URL);
		driver.manage().window().maximize();
		lp = new LoginPage(driver);
		hp = new HomePage(driver);
		rbp = new ReportBugPage(driver);
		
	    lp.login("Cody", "pass");
	    WebDriverWait wait = new WebDriverWait(driver, 2);
		wait.until(ExpectedConditions.urlContains("home"));
	    nb.clickReportNav();
	}

	@When("<user> fills out the entire form")
	@Test(dependsOnMethods = "testLoginAndNavigate")
	public void testFillForm() {
	    rbp.fillOutForm("Example", "Somewhere", "Its a bug", "click", "Low");
	}

	@When("<user> submits the form")
	@Test(dependsOnMethods = "testFillForm")
	public void testSubmitForm() {
	    rbp.submitForm();
	}

	@Then("the application saves the report")
	@Test(dependsOnMethods = "testSubmitForm", enabled = false)
	public void testPersistForm() {
		WebDriverWait wait = new WebDriverWait(driver, 4);
		wait.until(ExpectedConditions.urlContains("home"));
		
	    BugReport bg = this.bugReportService.findById(6);
	    
	    Assert.assertEquals(bg.getStatus(), "pending");
	}
	
	
	@AfterClass
	public void tearDown() {

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.quit();
	}
}
