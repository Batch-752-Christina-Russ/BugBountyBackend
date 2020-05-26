package com.revature.stepdefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.pageobjectmodel.LoginPage;
import com.revature.pageobjectmodel.Navbar;
import com.revature.pageobjectmodel.PendingBugPage;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.But;

public class ApprovalDenialStepDefinitions {
	
	private WebDriver driver;
	private final String URL = "localhost:4200/";
	private LoginPage loginPage;
	private Navbar navbar;
	private PendingBugPage pendingBugPage;


	@Test
	@Given("I navigate to the login page")
	public void i_navigate_to_the_login_page() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(URL);
		driver.manage().window().maximize();
		loginPage = new LoginPage(driver);
	}

	@When("I submit username and password")
	public void i_submit_username_and_password() {
		loginPage.login("admin", "pass");
		WebDriverWait wait = new WebDriverWait(driver, 2);
		wait.until(ExpectedConditions.urlContains("home"));
	}

	@Then("I should be logged in")
	public void i_should_be_logged_in() {
		Assert.assertEquals(this.driver.getCurrentUrl(), "http://localhost:4200/home");
	}

	@Given("admin is logged in")
	public void is_logged_in() {
		navbar = new Navbar(driver);
	}

	@Given("bug report exists")
	public void bug_report_exists() {
		navbar.clickPendingBug();
		WebDriverWait wait = new WebDriverWait(driver, 2);
		wait.until(ExpectedConditions.urlContains("pendingbug"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("card-19")));
	}

	@When("admin selects {string}")
	public void admin_selects(String string) {
		switch(string) {
		  case "approve":
			  WebElement approveButton = this.driver.findElement(By.id("card-19")).findElement(By.className("btn-success"));
			  approveButton.click();
			  break;
		  case "deny":
			  WebElement denyButton = this.driver.findElement(By.id("card-19")).findElement(By.className("btn-danger"));
			  denyButton.click();
		    break;
		  default:
		}

	}

	@Then("bug report status is {string}")
	public void bug_report_status_is(String string) {
	    // Write code here that turns the phrase above into concrete actions
	}

	@Then("bug report is {string}")
	public void bug_report_is(String string) {
	    // Write code here that turns the phrase above into concrete actions
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
		this.driver.quit();
	}


}
