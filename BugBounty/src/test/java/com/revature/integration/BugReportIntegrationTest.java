package com.revature.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.BugBountyApplication;
import com.revature.model.BugReport;
import com.revature.model.Role;
import com.revature.model.User;
import com.revature.service.BugReportService;
import com.revature.service.UserService;

@SpringBootTest(classes = BugBountyApplication.class)
@WebAppConfiguration
public class BugReportIntegrationTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private BugReportService bugReportService;
	
	@Autowired
	private UserService userService;

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

	/** Tests to see if /bugreport/save has new BugReport saved to the database. */
	@Test(dependsOnMethods = { "contextLoads" , "pendingStatusBugReportTest"})
	public void saveBugReportTest() {
		Role userRole = new Role(1, "user");
		User firstUser = new User(1, "Cody", "pass", 0, userRole);
		BugReport bugReport = new BugReport(0, firstUser, null, "BugBounty", "somewhere",
				"hitting any key causes machine to explode.", "open app and hit any key", "low", new Date(), "pending");
		try {
			ObjectMapper om = new ObjectMapper();
			String bugReportJson = om.writeValueAsString(bugReport);
			mockmvc.perform(
					post("/bugreport/save").contentType(MediaType.APPLICATION_JSON_VALUE).content(bugReportJson))
					.andExpect(status().isOk()).andDo(print()).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
		}
		BugReport br = this.bugReportService.findById(6);
		Assert.assertEquals(br.getApplication(), "BugBounty");
	}

	/**
	 * Tests to see if /bugreport/open returns all open bugs and nullifies passwords
	 */
	@Test(dependsOnMethods = { "contextLoads" })
	public void openStatusBugReportTest() {
		List<BugReport> bg = new ArrayList<>();
		
		try {
			MvcResult result = mockmvc.perform(get("/bugreport/open"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andDo(print()).andReturn();
			ObjectMapper om = new ObjectMapper();
			bg = om.readValue(result.getResponse().getContentAsString(), new TypeReference<List<BugReport>>() {});
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.assertEquals(bg.size(), 2);
		Assert.assertNull(bg.get(0).getReporter().getPassword());
	}
	
	/**
	 * Tests to see if /bugreport/pending returns all pending bugs and nullifies passwords
	 */
	@Test(dependsOnMethods = {"contextLoads"})
	public void pendingStatusBugReportTest()
	{
		List<BugReport> bg = new ArrayList<>();
		
		try {
			MvcResult result = mockmvc.perform(get("/bugreport/pending"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andDo(print()).andReturn();
			
			ObjectMapper om = new ObjectMapper();
			bg = om.readValue(result.getResponse().getContentAsString(), new TypeReference<List<BugReport>>() {});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Assert.assertEquals(bg.size(), 3);
		Assert.assertNull(bg.get(0).getReporter().getPassword());
	}
	
	/**
	 * Tests to see if /bugreport/approvedeny updates status of specified bug report to open
	 */
	@Test(dependsOnMethods = {"contextLoads", "pendingStatusBugReportTest", "openStatusBugReportTest"})
	public void approveBugReportTest() {
		BugReport bugReport = this.bugReportService.findById(1);
		Assert.assertEquals(bugReport.getStatus(), "pending");
		bugReport.setStatus("open");
		try {
			ObjectMapper om = new ObjectMapper();
			String bugReportJson = om.writeValueAsString(bugReport);
			mockmvc.perform(
					post("/bugreport/approvedeny").contentType(MediaType.APPLICATION_JSON_VALUE).content(bugReportJson))
					.andExpect(status().isOk()).andDo(print()).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
		}
		BugReport br = this.bugReportService.findById(1);
		Assert.assertEquals(br.getStatus(), "open");
	}
	
	/**
	 * Tests to see if /bugreport/approvedeny updates status of specified bug report to delete pending (deny)
	 */
	@Test(dependsOnMethods = {"contextLoads", "pendingStatusBugReportTest", "openStatusBugReportTest", "saveBugReportTest"})
	public void denyBugReportTest() {
		BugReport bugReport = this.bugReportService.findById(6);
		Assert.assertEquals(bugReport.getStatus(), "pending");
		bugReport.setStatus("delete");
		try {
			ObjectMapper om = new ObjectMapper();
			String bugReportJson = om.writeValueAsString(bugReport);
			mockmvc.perform(
					post("/bugreport/approvedeny").contentType(MediaType.APPLICATION_JSON_VALUE).content(bugReportJson))
					.andExpect(status().isOk()).andDo(print()).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
		}
		BugReport br = this.bugReportService.findById(6);
		Assert.assertNull(br);
	}
	
	/**
	 * Tests to see if /bugreport/resolve
	 */
	@Test(dependsOnMethods = {"contextLoads", "pendingStatusBugReportTest", "openStatusBugReportTest", "denyBugReportTest"})
	public void resolveBugReportTest() {
		String jsonBody = "{\"id\":1, \"username\":\"Connor\"}";
		User user = this.userService.findUserByUsername("Connor");
		int pointsBefore = user.getPoints();
		
		try {
			
			mockmvc.perform(post("/bugreport/resolve").contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonBody))
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andDo(print());

		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		//verify report is resolved
		BugReport report = this.bugReportService.findById(1);
		Assert.assertEquals(report.getStatus(), "resolved");
		
		User u2 = this.userService.findUserByUsername("Connor");
		int points = u2.getPoints();
		
		//verify Users points added
		Assert.assertTrue(points > pointsBefore);
		
	}
	

}
