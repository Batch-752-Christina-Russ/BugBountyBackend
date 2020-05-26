package com.revature.stepdefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.But;

public class ApprovalDenialStepDefinitions {
	
	private final String URL = "https://www.saucedemo.com/";
	private WebDriver driver;

	@BeforeClass
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(URL);
	}

	
	@Given("I navigate to the login page")
	public void i_navigate_to_the_login_page() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("I submit username and password")
	public void i_submit_username_and_password() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("I should be logged in")
	public void i_should_be_logged_in() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("{string} is logged in")
	public void is_logged_in(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("bug report exists")
	public void bug_report_exists() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("admin selects {string}")
	public void admin_selects(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("bug report status is {string}")
	public void bug_report_status_is(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("bug report is {string}")
	public void bug_report_is(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}



}
