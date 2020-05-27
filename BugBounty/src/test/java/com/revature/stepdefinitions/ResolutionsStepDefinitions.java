package com.revature.stepdefinitions;

import java.util.concurrent.TimeUnit;

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
import cucumber.api.java.en.When;

public class ResolutionsStepDefinitions{

	private final String URL = "http://localhost:4200/index";
	private WebDriver driver;

	@Autowired
	private BugReportService brs;

	private LoginPage loginPom;
	private Navbar navPom;
	private ResolveBugPage resolvePom;

	@Before
	public void setUp() {

	}

	@Given("I am on resolution page")
	@Test
	public void i_am_on_resolution_page() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(URL);

		loginPom = new LoginPage(driver);
		navPom = new Navbar(driver);
		resolvePom = new ResolveBugPage(driver);
		driver.manage().window().maximize();

		// login and go to resolution page
		loginPom.login("Admin", "pass");
		WebDriverWait wait = new WebDriverWait(driver, 2);
		wait.until(ExpectedConditions.urlToBe("http://localhost:4200/home"));
		navPom.clickResolveNav();
		wait.until(ExpectedConditions.urlContains("resolve"));
		// must be on resolve page
		String current = driver.getCurrentUrl();
		Assert.assertEquals(current, "http://localhost:4200/resolvebug");
	}

	@When("I press resolve")
	@Test(dependsOnMethods = { "i_am_on_resolution_page" })
	public void i_press_resolve() {
		this.resolvePom.resolveBug("2", "Colin");
	}

	@Then("bug_report is resolved")
	@Test(dependsOnMethods = { "i_press_resolve" })
	public void bug_report_is_resolved() {
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		BugReport report = brs.findById(2);
		Assert.assertEquals(report.getStatus(), "resolved");
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// Close the browser.
		driver.quit();
	}

	@AfterClass
	public void tearDown() {

	}
}
