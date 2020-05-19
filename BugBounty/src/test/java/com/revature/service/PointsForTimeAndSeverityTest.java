package com.revature.service;

import org.testng.annotations.Test;

import com.revature.model.BugReport;
import com.revature.model.Role;
import com.revature.model.User;
import com.revature.repository.BugReportRepository;
import com.revature.repository.UserRepository;

import static org.testng.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.testng.annotations.BeforeClass;

public class PointsForTimeAndSeverityTest {

	@MockBean
	UserRepository userRepository;

	@InjectMocks
	UserService userService;
	
	@MockBean
	BugReportRepository bugReportRepository;

	@InjectMocks
	BugReportService bugReportService;
	
	Role role = new Role(2, "Admin");
	User userForTest = new User(10, "username", "password", 50, role);
	int bugId = 5;
	Calendar.Builder calendarBuilder = new Calendar.Builder();
	Calendar calendar = calendarBuilder.build();
	Date localDate = subtractDays(calendar.getTime(), 10);
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
		assertEquals(10, this.bugReportService.calculateTimePoints(bugForTest));
	}

	@Test
	public void TestPointsForSeverity() {
		assertEquals(5, this.bugReportService.calculateSeverityPoints(bugForTest));
	}
	
	@Test
	public void TestPointsPersistedCorrectly() {

		Mockito.when(userRepository.UpdatePoints(userForTest.getId()).someMethod());
		
		assertEquals(50, userForTest.getPoints());
		this.userService.addPointsToUser(userForTest, 50);
		assertEquals(100, this.userService.addPointsToUser(userForTest).getPoints());
	}

}
