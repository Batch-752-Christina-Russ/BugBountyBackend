package com.revature.pageobjectmodel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ReportBugPage {
	@FindBy(id="appname")
	public WebElement appname;
	@FindBy(id="location")
	public WebElement location;
	@FindBy(id="description")
	public WebElement description;
	@FindBy(id="steps")
	public WebElement steps;
	@FindBy(id="severity")
	public WebElement severity;
	@FindBy(className="btn-primary")
	public WebElement buttonPrimary;
	public Actions act;
	public Select targetSeverity;
	
	
	public ReportBugPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        act = new Actions(driver);
        
    }
	
	public void selectSeverity(String inputSeverity) {
		targetSeverity = new Select(severity);
		targetSeverity.selectByVisibleText(inputSeverity);
	}
	
	public void fillOutForm(String appname, String location, String description,
					String steps, String inputSeverity) {
		act.sendKeys(this.appname, appname)
        .sendKeys(this.location, location)
        .sendKeys(this.description, description)
        .sendKeys(this.steps, steps)
        .perform();
        
        selectSeverity(inputSeverity);
	}
	
	public void submitForm() {
		act.click(this.buttonPrimary)
        .perform();	
	}
}
