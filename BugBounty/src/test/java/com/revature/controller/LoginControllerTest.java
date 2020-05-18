package com.revature.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.revature.model.Role;
import com.revature.model.User;
import com.revature.service.UserService;



@WebAppConfiguration
@SpringBootTest
public class LoginControllerTest {
		
	@Mock
	UserService userService;
	
	@InjectMocks
	UserController userController;
	User u1;
	Role r1;
	User u2;
	Gson gson;
	private MockMvc mockMvc;
	
	@BeforeSuite
	public void setUp() {
		r1 = new Role(1, "user");
		u1 = new User(1, "user", "password", 100, r1);
		u2 = new User(0, "user", "password", 0, null);
		gson = new Gson();
		
		userController = new UserController(userService);
		MockitoAnnotations.initMocks(this);
		
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
		
	}
	
	@Test
	public void testLogin() {		
		Mockito.when(userService.login(u2.getUsername(), u2.getPassword())).thenReturn(u1);
				
		try {
			mockMvc.perform( MockMvcRequestBuilders
					.post("/user/login")
					.characterEncoding("utf-8")
					.content(gson.toJson(u2))
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.accept(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().isOk())
					.andDo(print()).andReturn();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
