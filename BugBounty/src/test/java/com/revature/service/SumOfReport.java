package com.revature.service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


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

/**
 * <h1> Sum Of Report Test</h1>
* This is a test for the method sumBugReport in BugReportService.
* 
* This class mocks a BugReport and tests the sumBugReport method to ensure it returns the proper values
* 
* <p>
* This test mocks a User, BugReport, and Calendar values so that the value returned will be 30. It uses the test specfic method
* subtractDays to create a calendar and create the exact date for the test to be valid.
* 
* @author Colin Baldwin
* @author Jacob Short
* @version 1.0
* 
*/

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
	
	@BeforeSuite
	public void setUp() {
		role1 = new Role(1, "user");
		u1 = new User(1, "user", "password", 100, role1);
		Calendar calendar = Calendar.getInstance(); 
		Date localDate = subtractDays(calendar.getTime(), 15);
		// Severity Med (15) + Time (5) = 20
		r1 = new BugReport(1, u1, u1, "testproject","location", "this is a test", "steps 1 2 3", "med", localDate, "complete");
		MockitoAnnotations.initMocks(this);
	}
	
	public static Date subtractDays(Date date, int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, -days);
				
		return cal.getTime();
	}
	
	@Test
	public void SumBugReports() {
		
		Mockito.when(bugReportRepository.findById(1)).thenReturn(r1);

		//Sum = Severity + time
		Assertions.assertEquals(30, this.bugReportService.sumBugReport(1));

	}
}
