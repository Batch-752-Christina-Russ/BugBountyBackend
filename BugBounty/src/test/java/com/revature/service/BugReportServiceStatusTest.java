package com.revature.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.revature.model.BugReport;
import com.revature.model.Role;
import com.revature.model.User;
import com.revature.repository.BugReportRepository;

@SpringBootTest
public class BugReportServiceStatusTest {

	@Mock
	BugReportRepository bugReportRepository;
	
	@InjectMocks
	BugReportService bugReportService;
	BugReport bugReport;
	
	@BeforeSuite
	public void setUp() {
		int id = 1;
		Role adminRole = new Role(2, "admin");
		Role userRole = new Role(1, "user");
		User firstUser = new User(1, "darthvader", "lightsaber", 0, adminRole);
		User secondUser = new User(2, "billcipher", "triangle", 0, userRole);
		bugReport = new BugReport(id, firstUser, secondUser, "BugBounty", "somewhere",
				"hitting any key causes machine to explode.", "open app and hit any key", "LOW",
				new Date(), "open");
		
		bugReportService = new BugReportService();
		
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetBugReportByStatus() {
		
		List<BugReport> bg = new ArrayList<>();
		bg.add(bugReport);
		
		Mockito.when(bugReportRepository.findAllByStatus("open")).thenReturn(bg);
		
		//Assert that user is returned
		Assertions.assertEquals("somewhere", this.bugReportService.findByStatus("open").get(0).getLocation());
		Assertions.assertNull(this.bugReportService.findByStatus("open").get(0).getReporter().getPassword());
		Assertions.assertNull(this.bugReportService.findByStatus("open").get(0).getResolver().getPassword());
	}
}
