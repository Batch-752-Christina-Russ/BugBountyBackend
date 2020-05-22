package com.revature.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.revature.service.UserService;

@SpringBootTest(classes = BugBountyApplication.class)
@WebAppConfiguration
public class UserIntegrationTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private UserService userService;

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockmvc;

	// Sanity Test to see if Spring Context is actually loaded
	@Test
	public void contextLoads() {
	}

	@BeforeClass
	public void beforeUserIntegrationTest() {
		MockitoAnnotations.initMocks(this);
		mockmvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test(dependsOnMethods = { "saveUserTest", "contextLoads" })
	public void findtUserByUsernameTest() {
		User testU = this.userService.findUserByUsername("testusername");
		Assert.assertEquals(testU.getPassword(), "test");
	}

	@Test(dependsOnMethods = { "saveUserTest", "contextLoads" })
	public void getAllUsers() {
		List<User> users = this.userService.getAllUsers();

	}

	@Test(dependsOnMethods = { "contextLoads" }, enabled=false)
	public void getUserRankTest() {
		try {
			mockmvc.perform(get("/user/userrank" + "/testusername")).andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(dependsOnMethods = { "contextLoads" })
	public void saveUserTest() {
		Role r = new Role(1, "user");
		User u = new User(0, "testusername", "test", 0, r);
		this.userService.saveUser(u);

		try {
			ObjectMapper om = new ObjectMapper();
			String userJson = om.writeValueAsString(u);
			mockmvc.perform(post("/user/save").contentType(MediaType.APPLICATION_JSON_VALUE).content(userJson))
					.andExpect(status().isOk()).andDo(print()).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
