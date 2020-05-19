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
		
		System.out.println(r1.getDate());
		//Sum = Severity + time
		Assertions.assertEquals(30, this.bugReportService.sumBugReport(1));

	}
}
