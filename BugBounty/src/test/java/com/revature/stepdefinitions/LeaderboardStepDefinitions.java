package com.revature.stepdefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.revature.pageobjectmodel.LoginPage;
import com.revature.pageobjectmodel.Navbar;
import com.revature.pageobjectmodel.PendingBugPage;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class LeaderboardStepDefinitions {
	private WebDriver driver;
	private final String URL = "localhost:4200/";
	private LoginPage loginPage;
	private Navbar navbar;
	private PendingBugPage pendingBugPage;

	@Test
	@Given("<user> or <admin> is logged in")
	public void user_or_admin_is_logged_in() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(URL);
		driver.manage().window().maximize();
		loginPage = new LoginPage(driver);
		loginPage.login("Admin", "pass");
		WebDriverWait wait = new WebDriverWait(driver, 2);
		wait.until(ExpectedConditions.urlContains("home"));

	}
	@Test
	@Then("<leaderboard> of users sorted by <user> total points")
	public void leaderboard_of_users_sorted_by_user_total_points() {
		Assert.assertTrue(this.driver.findElement(By.tagName("tbody")).isDisplayed());
		int temp = Integer.parseInt(this.driver.findElement(By.id("points-1")).getText());
		for (int i = 2; i < 11; i++) {
			Assert.assertTrue(Integer.parseInt(this.driver.findElement(By.id("points-"+i)).getText())<= temp);
			temp = Integer.parseInt(this.driver.findElement(By.id("points-"+i)).getText());
		}

	}
	@Test
	@Then("<user> is listed at their rank")
	public void user_is_listed_at_their_rank() {
		Assert.assertTrue(this.driver.findElement(By.tagName("tbody")).isDisplayed());
		int temp = Integer.parseInt(this.driver.findElement(By.id("rank-1")).getText());
		for (int i = 2; i < 11; i++) {
			Assert.assertTrue(Integer.parseInt(this.driver.findElement(By.id("rank-"+i)).getText())>= temp);
			temp = Integer.parseInt(this.driver.findElement(By.id("rank-"+i)).getText());
		}		
		
		
		//Close the browser.
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.driver.quit();
	}
	

}
