package com.revature.service;

import java.util.Date;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.testng.annotations.BeforeClass;

import com.revature.model.BugReport;
import com.revature.model.Role;
import com.revature.model.User;
import com.revature.repository.BugReportRepository;

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

}
