package com.revature.service;

import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.annotations.BeforeClass;

import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.testng.annotations.AfterClass;

import com.revature.BugBountyApplication;
import com.revature.model.BugReport;
import com.revature.model.Role;
import com.revature.repository.BugReportRepository;
import com.revature.service.BugReportService;
import com.revature.model.User;

@SpringBootTest
public class BugReportServiceTest {

	@MockBean
	BugReportRepository bugReportRepository;
	
	@InjectMocks
	BugReportService bugReportService;


	BugReport bugReport;

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
	public void testSaveBugReport() {
		/*System.out.println(bugReport);
		Mockito.when(this.bugReportRepository.save(bugReport)).thenReturn(bugReport);
		Assert.assertEquals("BugBounty", bugReport.getApplication());*/
	}

}
