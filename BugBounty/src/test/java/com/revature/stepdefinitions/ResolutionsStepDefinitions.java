package com.revature.stepdefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.revature.model.BugReport;
import com.revature.pageobjectmodel.LoginPage;
import com.revature.pageobjectmodel.Navbar;
import com.revature.pageobjectmodel.ResolveBugPage;
import com.revature.service.BugReportService;
import com.revature.service.UserService;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"server.port=8080"})
@ContextConfiguration(loader = SpringBootContextLoader.class)
public class ResolutionsStepDefinitions extends AbstractTestNGSpringContextTests  {

	private final String URL = "http://localhost:4200/index";
	private WebDriver driver;
	
	@Autowired
	private BugReportService brs;
	@Autowired
	private UserService urs;
	
	//POMs
	private LoginPage loginPom;
	private Navbar navPom;
	private ResolveBugPage resolvePom;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(URL);
		
		//poms
		loginPom = new LoginPage(driver);
		navPom = new Navbar(driver);
		resolvePom = new ResolveBugPage(driver);
		
		//login and go to resolution page
		loginPom.login("Admin", "pass");
		WebDriverWait wait = new WebDriverWait(driver, 2);
		wait.until(ExpectedConditions.urlToBe("http://localhost:4200/home"));
		navPom.clickResolveNav();
		wait.until(ExpectedConditions.urlContains("resolve"));
	}


@Given("I am on resolution page")
@Test
public void i_am_on_resolution_page() {
   //must be on resolve page
	String current = driver.getCurrentUrl();
	Assert.assertEquals(current, "http://localhost:4200/resolvebug"); 
}

@When("I press resolve")
@Test(dependsOnMethods = {"i_am_on_resolution_page"})
@Commit
public void i_press_resolve() {
   this.resolvePom.resolveBug("2", "Colin");
}

@Then("bug_report is resolved")
@Test(dependsOnMethods = {"i_press_resolve"})
public void bug_report_is_resolved() {
   BugReport report = brs.findById(2);
   report.setStatus("resolved"); 
   Assert.assertEquals(report.getStatus(), "resolved");
}

@After
public void tearDown() {
	driver.quit();
}
}
