package com.revature.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.List;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.BugBountyApplication;
import com.revature.model.Role;
import com.revature.model.User;
import com.revature.model.BugReport;
import com.revature.service.BugReportService;

@SpringBootTest(classes = BugBountyApplication.class)
@WebAppConfiguration
public class BugReportIntegrationTest extends AbstractTestNGSpringContextTests{
	
	@Autowired
	private BugReportService bugReportService;

	private MockMvc mockmvc;

	@Autowired
    private WebApplicationContext wac;
	
	// Sanity Test to see if Spring Context is actually loaded
	@Test
	public void contextLoads() {
		
	}

	@BeforeClass
	public void beforeBugReportIntegrationTest() {
			MockitoAnnotations.initMocks(this);
			mockmvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	
	@Test(dependsOnMethods = { "contextLoads" })
	public void saveBugReportTest() {
		Role adminRole = new Role(2, "admin");
		User firstUser = new User(1, "stephanie", "test", 0, adminRole);
		BugReport bugReport = new BugReport(1, firstUser, null, "BugBounty", "somewhere",
				"hitting any key causes machine to explode.", "open app and hit any key", "LOW",
				new Date(), "open");
		try
		{
			ObjectMapper om = new ObjectMapper();
			String bugReportJson = om.writeValueAsString(bugReport);
			mockmvc.perform(post("/bugreport/save").contentType(MediaType.APPLICATION_JSON_VALUE).content(bugReportJson))
			.andExpect(status().isOk())
			.andDo(print()).andReturn();
		}catch(
		Exception e)
		{
			e.printStackTrace();
		}
	}

}
