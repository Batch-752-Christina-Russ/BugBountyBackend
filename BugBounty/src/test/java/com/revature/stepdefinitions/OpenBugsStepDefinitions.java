package com.revature.stepdefinitions;

import com.revature.pageobjectmodel.LoginPage;
import com.revature.pageobjectmodel.HomePage;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class OpenBugsStepDefinitions {

	private final String URL = "http://localhost:4200";
	private WebDriver driver;
	private LoginPage lp;
	private HomePage hp;

	@Before
	public void setup() {
		/*
		 * System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		 * driver = new ChromeDriver(); driver.get(URL); lp = new LoginPage(driver);
		 */
	}

	@Given("you are in the login page")
	public void testLoginPage() {

		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(URL);
		lp = new LoginPage(driver);
		hp = new HomePage(driver);
		

		Assert.assertEquals(driver.getCurrentUrl(), "http://localhost:4200/index");
	}

	@Given("<user> is logged in")
	@Test(dependsOnMethods = "testLoginPage")
	public void testUserLoggingIn() {
		lp.login("Cody", "pass");

	}

	@When("<user> attempts to click the first more button") 
	@Test(dependsOnMethods = "testUserLoggingIn")
	public void testUserClickMore() { 
		driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
		hp.clickMoreButton(0);
	}

	@Then("<user> can see a more descriptive bug report") 
	@Test(dependsOnMethods = "testUserClickMore")
	public void testUserCheckModal() { 
		
		Assert.assertNotNull(this.driver.findElement(By.className("modal-content")));

	}

	
	@Given("<admin> is logged in")
	@Test(dependsOnMethods = "testLoginPage")
	public void testAdminLoggingIn() {
		lp.login("Admin", "pass");

		Assert.assertEquals(driver.getCurrentUrl(), "http://localhost:4200/index");
	}

	@When("<admin> attempts to click the first more button") 
	@Test(dependsOnMethods = "testAdminLoggingIn")
	public void testAdminClickMore() { 
		driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
		hp.clickMoreButton(0);
	}

	@Then("<admin> can see a more descriptive bug report") 
	@Test(dependsOnMethods = "testAdminClickMore")
	public void testAdminCheckModal() { 
		
		Assert.assertNotNull(this.driver.findElement(By.className("modal-content")));
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
