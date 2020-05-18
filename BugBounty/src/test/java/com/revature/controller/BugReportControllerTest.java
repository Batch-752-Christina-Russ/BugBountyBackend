package com.revature.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.revature.BugBountyApplication;
import com.revature.model.BugReport;
import com.revature.model.Role;
import com.revature.model.User;
import com.revature.service.BugReportService;

@SpringBootTest(classes = BugBountyApplication.class)
public class BugReportControllerTest {

	@Mock
	BugReportService bugReportService;
	
	@InjectMocks
	BugReportController bugReportController;
	
	
	private MockMvc mockmvc;
	
	private BugReport bugReport;
	
	@Before
	public void setUp() {
		
		//Don't forget to initialize those Mockito annotations!
		MockitoAnnotations.initMocks(this);
		
		//Let's build our mock controller!
		mockmvc = MockMvcBuilders.standaloneSetup(bugReportController).build();
		
		//Initialize some mock data:
		
		int id = 1;
		Role adminRole = new Role(0, "ADMIN");
		Role userRole = new Role(1, "USER");
		User firstUser = new User(1, "darthvader", "lightsaber", 0, adminRole);
		User secondUser = new User(2, "billcipher", "triangle", 0, userRole);
		bugReport = new BugReport(id, firstUser, secondUser, "BugBounty", "somewhere",
				"hitting any key causes machine to explode.", "open app and hit any key", "LOW",
				new Date(System.currentTimeMillis()), "SUBMITTED");
	}
	
//	@Test
//public void testGetAllBugReports() {
//		
//		Mockito.when(this.bugReportService.findAllBugReports()).thenReturn(bugReportList);
//		
//		//Now let's make a request to our mock controller.
//		try {
//			mockmvc.perform(get("/all")).andExpect(status().isOk())
//			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//			.andDo(print()).andReturn();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
//	@Test
//	public void testFindById() {
//		int id = 1;
//		Mockito.when(this.bugReportService.findById(id )).thenReturn(bugReport);
//		
//		
//	}
	
	@Test
	public void testSaveBugReport() {
	
		
	    Mockito.when(this.bugReportService.saveBugReport(bugReport)).thenReturn(true);
	    try {
			mockmvc.perform(post("/bugreport/new")).andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andDo(print()).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
		}
		   
	}
