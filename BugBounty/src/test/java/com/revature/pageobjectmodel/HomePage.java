package com.revature.pageobjectmodel;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	@FindBy(css="table:first-of-type")
	public WebElement leaderboardTable;
	
	@FindBy(css="table:last-of-type")
	public WebElement userRankRow;
	
	@FindBy(className="col-sm-12 col-md-6 col-lg-4")
	public List<WebElement> openBugs;
	
	@FindBy(className="btn-primary")
	public List<WebElement> moreButtons;
	
	@FindBy(id="logoutnav")
	public WebElement logout;
	
	@FindBy(className="btn-secondary")
	public List<WebElement> closeButton;
	
	@FindBy(id = "reportnav")
	public WebElement reportnav;
	public Actions act;
	
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		act = new Actions(driver);
	}
	
	public void clickMoreButton(int id) {
		act.click(this.moreButtons.get(id)).perform();
	}
	
	public void clickReportNav() {
		act.click(this.reportnav).perform();
	}
	
}
