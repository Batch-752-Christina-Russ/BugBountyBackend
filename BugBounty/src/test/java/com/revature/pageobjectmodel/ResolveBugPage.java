package com.revature.pageobjectmodel;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResolveBugPage {
	
	@FindBy(id="bugId")
	public WebElement bugIdInputBox;
	
	@FindBy(id="username")
	public WebElement resolverInputBox;
	
	@FindBy(css="button.btn:first-of-type")
	public WebElement resolveBugButton;
	
	@FindBy(className="col-sm-12 col-md-6 col-lg-4")
	public List<WebElement> openBugs;
	
	@FindBy(css="div#card-btn button")
	public List<WebElement> openBugButtons;
	
	@FindBy(css="div.modal-footer button")
	public List<WebElement> modalCloseButtons;
	
	public Actions act;
		
	public ResolveBugPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		act = new Actions(driver);
	}
	
	public void resolveBug(String id, String resolver) {
		act.sendKeys(this.bugIdInputBox, id)
		.sendKeys(this.resolverInputBox, resolver)
		.click(this.resolveBugButton)
		.perform();
	}
	
	public void openBugModals() {
		for(WebElement e : openBugButtons) {
			e.click();
		}
	}
}
