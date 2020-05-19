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
	private List<BugReport> pendingBugReports =  new ArrayList<>();;
	
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
	
	@Test
	public void testGetAll() {

		Mockito.when(this.bugReportRepository.findAllByStatus("pending")).thenReturn(pendingBugReports);
		
		Assert.assertEquals(pendingBugReports, this.bugReportService.findByStatus("pending"));
				
	}
}
