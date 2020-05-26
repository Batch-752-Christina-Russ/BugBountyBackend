package com.revature.service;

import static org.mockito.Mockito.atLeastOnce;

import java.util.ArrayList;
import java.util.List;

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

public class ApproveDenyBugServiceTest {
	@Mock
	BugReportRepository bugReportRepository;
	
	@InjectMocks
	BugReportService bugReportService;
	
	private User user = new User(6, "Bob", "password", 40, new Role(1, "user"));
	private BugReport bugReport = new BugReport(8, user, "Test", "Test", "Test", "Test", "Test", null, "open");
	private List<BugReport> pendingBugReports =  new ArrayList<>();
	
	@BeforeSuite
	public void setUp() {
		
		bugReportService = new BugReportService();
		//initialize Mockito
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testUpdateBugReportStatus() {
		bugReportService.updateBugReportStatus(bugReport);
		Mockito.verify(bugReportRepository, atLeastOnce()).updateStatus(8, "open");
	}
	
	@Test
	public void testDeleteBugReport() {
		bugReportService.deleteBugReport(8);
		Mockito.verify(bugReportRepository, atLeastOnce()).deleteById(8);
	}

}
