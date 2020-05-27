package com.revature.stepdefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.revature.pageobjectmodel.LoginPage;
import com.revature.pageobjectmodel.Navbar;

public class ApprovalDenialStepDefinitions {
	
	private WebDriver driver;
	private final String URL = "localhost:4200/";
	private LoginPage loginPage;
	private Navbar navbar;

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
		loginPage.login("Admin", "pass");
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
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("card-5")));
	}

	@When("admin selects {string}")
	public void admin_selects(String string) {
		switch(string) {
		  case "approve":
			  WebElement approveButton = this.driver.findElement(By.id("card-1")).findElement(By.className("btn-success"));
			  approveButton.click();
			  
			  break;
		  case "deny":
			  WebElement denyButton = this.driver.findElement(By.id("card-3")).findElement(By.className("btn-danger"));
			  denyButton.click();
		    break;
		  default:
		}
		
	}

	@Then("bug report status is {string}")
	public void bug_report_status_is(String string) {
		boolean card;
		WebDriverWait wait = new WebDriverWait(driver, 2);
		switch(string) {
		  case "open":
			  try{
					wait.until(ExpectedConditions.invisibilityOf(this.driver.findElement(By.id("card-1"))));

					 card = this.driver.findElement(By.id("card-1")).isDisplayed();
				}catch( Exception e) {
					card = false;
				}
				
			    Assert.assertEquals(card, false);
			    this.driver.quit();
			  
			  break;
		  case "deleted":
			  try{
					wait.until(ExpectedConditions.invisibilityOf(this.driver.findElement(By.id("card-3"))));

					 card = this.driver.findElement(By.id("card-3")).isDisplayed();
				}catch( Exception e) {
					card = false;
				}
				
			    Assert.assertEquals(card, false);
			    this.driver.quit();
			  
			  break;
		  default:
		}

	}

	@AfterClass
	public void tearDown() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//Close the browser.
		this.driver.quit();
	}


}
