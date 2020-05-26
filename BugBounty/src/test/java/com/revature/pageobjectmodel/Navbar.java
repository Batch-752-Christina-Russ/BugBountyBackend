package com.revature.pageobjectmodel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Navbar {
	
	@FindBy(id = "resolvenav")
	public WebElement resolveNav;
	@FindBy(id = "pendingnav")
	public WebElement pendingNav;
	@FindBy(id = "reportnav")
	public WebElement reportNav;
	@FindBy(id = "logoutnav")
	public WebElement logoutNav;

	public Navbar(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void clickResolveNav() {
		this.resolveNav.click();
	}
	public void clickPendingBug() {
		this.pendingNav.click();
	}
	public void clickReportNav() {
		this.reportNav.click();
	}
	public void clickLogoutNav() {
		this.logoutNav.click();
	}

}