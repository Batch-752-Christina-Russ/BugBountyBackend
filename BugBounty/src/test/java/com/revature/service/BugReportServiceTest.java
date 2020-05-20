package com.revature.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;


import com.revature.model.BugReport;
import com.revature.model.Role;
import com.revature.model.User;
import com.revature.repository.BugReportRepository;


public class BugReportServiceTest {

	@Mock
	BugReportRepository bugReportRepository;
	
	@InjectMocks
	BugReportService bugReportService;
	
	//global variables
	private User user = new User(6, "Bob", "password", 40, new Role(1, "user"));
	private List<BugReport> pendingBugReports =  new ArrayList<>();
  BugReport bugReport;
	
	@BeforeSuite
	public void setup() {
		//get test objects
		
		pendingBugReports.add(new BugReport(1, user , "yadayada", "error file", "bad code",
				"4", "high", new Date(), "pending"));
		pendingBugReports.add(new BugReport(2, user , "dddddddddd", "error file", "bad code",
				"4", "high", new Date(), "pending"));
		pendingBugReports.add(new BugReport(3, user , "sadfasdfasd", "error file", "bad code",
				"4", "high", new Date(), "pending"));

		
		//initialize Mockito
		MockitoAnnotations.initMocks(this);
	}
  
  @BeforeClass
	public void beforeClass() {
		Role adminRole = new Role(2, "admin");
		Role userRole = new Role(1, "user");
		User firstUser = new User(1, "darthvader", "lightsaber", 0, adminRole);
		User secondUser = new User(2, "billcipher", "triangle", 0, userRole);
		bugReport = new BugReport(1, firstUser, secondUser, "BugBounty", "somewhere",
				"hitting any key causes machine to explode.", "open app and hit any key", "LOW", new Date(), "pending");
		bugReportService = new BugReportService();
		MockitoAnnotations.initMocks(this);
	}

	
	@Test
	public void testGetAll() {

		Mockito.when(this.bugReportRepository.findAllByStatus("pending")).thenReturn(pendingBugReports);
		
		Assert.assertEquals(pendingBugReports, this.bugReportService.findByStatus("pending"));
				
	}
}
