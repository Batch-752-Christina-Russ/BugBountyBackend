package com.revature.service;

import static org.testng.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.model.BugReport;
import com.revature.model.Role;
import com.revature.model.User;
import com.revature.repository.BugReportRepository;
import com.revature.repository.UserRepository;

public class PointsForTimeAndSeverityTest {

	@Mock
	UserRepository userRepository;

	@InjectMocks
	UserService userService;
	
	@Mock
	BugReportRepository bugReportRepository;

	@InjectMocks
	BugReportService bugReportService;
	
	Role role = new Role(2, "Admin");
	User userForTest = new User(10, "username", "password", 50, role);
	int bugId = 5;
	Calendar calendar = Calendar.getInstance(); 
	Date localDate = subtractDays(calendar.getTime(), 15);
	BugReport bugForTest = 
	new BugReport(bugId, userForTest, userForTest, "application", "location", "description", "steps", "low", localDate, "status");
			   
	@BeforeClass
		public void beforeClass() {
		userService = new UserService();
		bugReportService = new BugReportService();
		
		MockitoAnnotations.initMocks(this);
	}
	
	public static Date subtractDays(Date date, int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, -days);
				
		return cal.getTime();
	}
	  
	
	@Test
	public void TestGetBugReport() {
		Mockito.when(bugReportRepository.findById(bugId)).thenReturn(bugForTest);
		assertEquals(bugForTest, this.bugReportService.findById(bugId));
	}
	
	@Test
	public void TestPointsForTime() {
		assertEquals(15, this.bugReportService.calculateTimePoints(bugForTest));
	}

	@Test
	public void TestPointsForSeverity() {
		assertEquals(5, this.bugReportService.calculateSeverityPoints(bugForTest));
	}

}
