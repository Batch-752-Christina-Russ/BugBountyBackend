package com.revature.service;

import java.sql.Date;

import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.revature.model.BugReport;
import com.revature.model.Role;
import com.revature.model.User;
import com.revature.repository.BugReportRepository;
import com.revature.repository.UserRepository;

//test that sum of 2 reports has correct value
public class SumOfReport {
	@Mock
	BugReportRepository bugReportRepository;
	
	@InjectMocks
	BugReportService bugReportService;
	BugReport r1;
	BugReport r2;
	User u1;
	Role role1;
	
	@SuppressWarnings("deprecation")
	@BeforeSuite
	public void setUp() {
		role1 = new Role(1, "user");
		u1 = new User(1, "user", "password", 100, role1);
		// Severity Med (15) + Time (5) = 20
		r1 = new BugReport(1, u1, u1, "testproject","location", "this is a test", "steps 1 2 3", "med", new Date(2019,3,4), "complete");
		
		
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testSumBugReports() {
		
		Mockito.when(bugReportRepository.findById(1)).thenReturn(r1);
		
		//Sum = Severity + time
		Assertions.assertEquals(20, this.bugReportService.sumBugReport(1));
	}
}
