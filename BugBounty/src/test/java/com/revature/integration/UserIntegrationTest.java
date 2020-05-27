package com.revature.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
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

	/**Tests to see if /user/save has new User saved to database*/
	@Test(dependsOnMethods = { "contextLoads" })
	public void saveUserTest() {
		Role r = new Role(1, "user");
		User u = new User(0, "testusername", "test", 0, r);
		try {
			ObjectMapper om = new ObjectMapper();
			String userJson = om.writeValueAsString(u);
			mockmvc.perform(post("/user/save").contentType(MediaType.APPLICATION_JSON_VALUE).content(userJson))
					.andExpect(status().isOk()).andDo(print()).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
		}
		User testU = this.userService.findUserByUsername("testusername");
		Assert.assertEquals(testU.getRole().getName(), "user");
		Assert.assertEquals(testU.getPassword(), "test");
	}
	

	/**Tests to see if /user/userrank/{username} works*/
	/** Test depends on specific PostgreSQL query in Repository*/
	@Test(dependsOnMethods = { "contextLoads" }, enabled=false)
	public void getUserRankTest() {
		int rank = 0;
		try {
			MvcResult result = mockmvc.perform(get("/user/userrank" + "/Cody"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andDo(print()).andReturn();
			
			//Get result
			ObjectMapper om = new ObjectMapper();
			rank = om.readValue(result.getResponse().getContentAsString(), new TypeReference<Integer>() {});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//should return an int value greater than 0
		Assert.assertTrue(rank > 0);
	}
	
	/**Tests to see if user/topten works*/
	@Test(dependsOnMethods = { "contextLoads"})
	public void getTopTenTest() {
		//list to hold topTen
		List<User> topTenList = new ArrayList<User>();
		
		try {
			MvcResult result = mockmvc.perform(get("/user/topten"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andDo(print()).andReturn();
			
			//getList
			ObjectMapper om = new ObjectMapper();
			topTenList = om.readValue(result.getResponse().getContentAsString(), new TypeReference<List<User>>() {});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//test size = 10
		Assert.assertEquals(topTenList.size(), 10);
		
		//verify passwords are null
		Assert.assertNull(topTenList.get(0).getPassword());
		
		//verify value to left is larger or equal
		Assert.assertTrue(topTenList.get(0).getPoints() >= topTenList.get(1).getPoints());
	}
	
	/** Tests to see if login works e2e*/
	@Test(dependsOnMethods = { "contextLoads"})
	public void loginTest() {
		//User object to return
		User user = new User();
		
		try {
			//Json to send
			ObjectMapper om = new ObjectMapper();
			User test = new User(0, "Stephanie", "pass", 0, new Role(1, "user"));
			String userJson = om.writeValueAsString(test);
			
			MvcResult result = mockmvc.perform(post("/user/login").contentType(MediaType.APPLICATION_JSON_VALUE).content(userJson))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andDo(print()).andReturn();
			
			//Get result
			user = om.readValue(result.getResponse().getContentAsString(), new TypeReference<User>() {});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Verify user is the same & the password is null
		Assert.assertEquals(user.getUsername(), "Stephanie");
		Assert.assertNull(user.getPassword());
	}
}
