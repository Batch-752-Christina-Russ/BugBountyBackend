package com.revature.pageobjectmodel;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	@FindBy(css="table:first-of-type")
	public WebElement leaderboardTable;
	
	@FindBy(css="table:last-of-type")
	public WebElement userRankRow;
	
	@FindBy(className="col-sm-12 col-md-6 col-lg-4")
	public List<WebElement> openBugs;
	
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
}
