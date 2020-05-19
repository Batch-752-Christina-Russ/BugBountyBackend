package com.revature.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.BugReport;
import com.revature.model.Role;
import com.revature.model.User;
import com.revature.service.BugReportService;

@WebAppConfiguration
@SpringBootTest
public class BugReportControllerTest {

	@Mock
	private BugReportService bugReportService;

	@InjectMocks
	private BugReportController bugReportController;

	
	private List<BugReport> pendingBugReports =  new ArrayList<>();;
	private User user = new User(6, "Bob", "password", 40, new Role(1, "user"));
	private MockMvc mockMvc;
	
	@BeforeSuite
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(bugReportController).build();
		pendingBugReports.add(new BugReport(1, user , "yadayada", "error file", "bad code",
				"4", "high", new Date(), "pending"));
		pendingBugReports.add(new BugReport(2, user , "dddddddddd", "error file", "bad code",
				"4", "high", new Date(), "pending"));
		pendingBugReports.add(new BugReport(3, user , "sadfasdfasd", "error file", "bad code",
				"4", "high", new Date(), "pending"));
	}
  
	@Test
	public void testGetAllPending() {
		Mockito.when(this.bugReportService.findByStatus("pending")).thenReturn(pendingBugReports);
		try {
			mockMvc.perform(get("/bugreport/pending")).andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andDo(print()).andReturn();
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}
	
	@Test
	public void testApproveDeny() {
		try {
			mockMvc.perform(post("/bugreport/approvedeny")
			.content(asJsonString(new BugReport(3, user , "sadfasdfasd", "error file", "bad code","4", "high", new Date(), "approved")))
			.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
			.andDo(print()).andReturn();
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}
	public static String asJsonString(final Object obj) {
	    try {
	        final ObjectMapper mapper = new ObjectMapper();
	        final String jsonContent = mapper.writeValueAsString(obj);
	        return jsonContent;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
}
