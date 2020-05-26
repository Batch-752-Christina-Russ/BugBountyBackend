package com.revature.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.model.BugReport;
import com.revature.model.Role;
import com.revature.model.User;
import com.revature.service.BugReportService;

@SpringBootTest
public class BugReportStatusControllerTest {

	@Mock
	private BugReportService bugReportService = new BugReportService();
	
	@InjectMocks
	private BugReportController testController;
	
	private MockMvc mockMVC;
	
	BugReport bugReport;

	List<BugReport> bg = new ArrayList<>();
		
  
	@BeforeClass
	public void beforeClass() {
		
		//Initialize some mock data:
		
		Role adminRole = new Role(2, "admin");
		Role userRole = new Role(1, "user");
		User firstUser = new User(1, "darthvader", "lightsaber", 0, adminRole);
		User secondUser = new User(2, "billcipher", "triangle", 0, userRole);
		bugReport = new BugReport(1, firstUser, secondUser, "BugBounty", "somewhere",
				"hitting any key causes machine to explode.", "open app and hit any key", "LOW",
				new Date(), "pending");
		
		bg.add(bugReport);
		
		testController = new BugReportController();
		MockitoAnnotations.initMocks(this);
		mockMVC = MockMvcBuilders.standaloneSetup(testController).build();
		
		
	}
  
	@Test
	public void testFindBugReportByStatus() {
		
		when(this.bugReportService.findByStatus("pending")).thenReturn(bg);	
		try {
			
			
			mockMVC.perform(get("/bugreport/" + "pending"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andDo(print())
			.andReturn();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
