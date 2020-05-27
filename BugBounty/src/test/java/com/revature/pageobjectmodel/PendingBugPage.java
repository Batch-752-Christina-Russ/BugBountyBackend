package com.revature.pageobjectmodel;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class PendingBugPage {

	@FindBy(className = "btn-primary")
	public List<WebElement> moreButtons;
	@FindBy(className = "btn-success")
	public List<WebElement> approveButtons;
	@FindBy(className = "btn-danger")
	public List<WebElement> denyButtons;
	
	public Actions act;
	
	public PendingBugPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		act = new Actions(driver);
	}
	
	public void clickMoreButton(int id) {
		act.click(this.moreButtons.get(id)).perform();
	}
	
	public void clickApproveButton(int id) {
		act.click(this.approveButtons.get(id)).perform();
	}
	
	public void clickAllApproveButtons() {
		for(WebElement we : approveButtons) {
			act.click(we).perform();
		}
	}
	
	public void clickDenyButton(int id) {
		act.click(this.denyButtons.get(id)).perform();
	}
	
	public void clickAllDenyButtons() {
		for(WebElement we : denyButtons) {
			act.click(we).perform();
		}
	}
}
