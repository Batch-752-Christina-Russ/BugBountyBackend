package com.revature.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.revature.BugBountyApplication;
import com.revature.model.BugReport;
import com.revature.model.Role;
import com.revature.model.User;
import com.revature.service.BugReportService;

@SpringBootTest(classes= BugBountyApplication.class)
@WebAppConfiguration
public class ResolutionControllerTest {
	
	@Mock
	private BugReportService userService = new BugReportService();
	
	@InjectMocks
	private BugReportController	bugReportController;
	
	private MockMvc mockMvc;
	private BugReport bugReport;
	
	@BeforeSuite
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(bugReportController).build();
		bugReport = new BugReport(5,
				new User(5, "bob", "password", 50, new Role(1, "user")),
				new User(),
				"application", "location", "description", "steps", "low",
				new Date(), "open");
		User user = new User(20, "loki", "notThor", 32, new Role(1, "user"));
		
	}
	
	@Test
	public void ControllerTest() {
		Mockito.when(this.userService.resolve(5, "loki")).thenReturn(true);
		
		try {
			String jsonBody = "{\"id\":5, \"username\":\"loki\"}";
			
			
			mockMvc.perform(post("/bugreport/resolve").contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonBody))
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andDo(print());

		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
