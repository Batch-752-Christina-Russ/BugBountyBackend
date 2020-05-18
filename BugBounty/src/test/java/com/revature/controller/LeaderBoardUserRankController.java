package com.revature.controller;

import org.testng.annotations.Test;

import com.revature.model.Role;
import com.revature.model.User;
import com.revature.service.UserService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.persistence.Column;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeClass;

// We will be testing the User-Rank end point of the user controller
public class LeaderBoardUserRankController {
	
	@Mock
	private UserService dummy = new UserService();
	
	@InjectMocks
	private UserController testController;
	
	private MockMvc mockMVC;
	
	Role role = new Role(2, "Admin");
	User userForTest = new User(5, "bob", "password", 150, role);
	Object object;
	
  @Test
  public void f() {
		try {
			mockMVC.perform(get("/user/userrank")).andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andDo(print()).andReturn();
		} catch(Exception e) {
			e.printStackTrace();
		}	
  }
  
  @BeforeClass
  public void beforeClass() {
	  testController = new UserController();
		MockitoAnnotations.initMocks(this);
		mockMVC = MockMvcBuilders.standaloneSetup(testController).build();
		object = new ObjectFromRankAndUser();
		when(dummy.getRankAndUser()).thenReturn(object);	
  }

  // the user rank function returns a generic object with these fields
  public class ObjectFromRankAndUser{
		int points = 150;
		String username = "bob";
		int rank = 5;
		
		ObjectFromRankAndUser() {};
  }
  
}



