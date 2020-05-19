package com.revature.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;


import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.revature.model.Role;
import com.revature.model.User;
import com.revature.service.UserService;

//@WebMvcTest(UserController.class)
@WebAppConfiguration
@SpringBootTest

public class Toptencontrollertest {
	@Autowired
	private RestTemplate restTemplate;
	
	@Mock
	private UserService userService;
	
	@InjectMocks
	private UserController userController;
	
	private List<User> top10;
	
	private MockMvc mockMvc;
	private Role role;
	
	@BeforeSuite
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
		role = new Role(1, "user");
		top10 = new ArrayList<>();
		
		top10.add(new User(0, "Not the right user", "password", 100, role));
        top10.add(new User(1, "not him either", "password", 90, role));
        top10.add(new User(2, "not her", "password", 80, role));
        top10.add(new User(3, "Still the wrong guy", "password", 70, role));
        top10.add(new User(4, "Worng User", "password", 60, role));
        top10.add(new User(5, "IAmTheRightTestUser", "password", 50, role));
        top10.add(new User(6, "Bob", "password", 40, role));
        top10.add(new User(7, "Joebob", "password", 30, role));
        top10.add(new User(8, "Jill", "password", 20, role));
        top10.add(new User(9, "TooManyUsers", "password", 10, role));
        top10.add(new User(10, "Finally", "password", 0, role));
	}
	
	@Test
	public void TopTenControllerTest() {
		Mockito.when(this.userService.getTopTen()).thenReturn(top10);
		
		try {
			mockMvc.perform(get("/user/topten")).andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andDo(print()).andReturn();

		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
