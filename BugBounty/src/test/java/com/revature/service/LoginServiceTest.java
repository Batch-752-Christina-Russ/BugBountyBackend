package com.revature.service;

import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.revature.model.Role;
import com.revature.model.User;
import com.revature.repository.UserRepository;

//@ContextConfiguration(locations = "classpath:testContext.xml")
public class LoginServiceTest {
	
	@Mock
	UserRepository userRepository;
	
	@InjectMocks
	UserService userService;
	User u1;
	Role r1;
	
	@BeforeSuite
	public void setUp() {
		r1 = new Role(1, "user");
		u1 = new User(1, "user", "password", 100, r1);
		
		userService = new UserService();
		
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testLogin() {
		
		Mockito.when(userRepository.findByUsernameAndPassword("user", "password")).thenReturn(u1);
		
		//Assert that user is returned
		Assertions.assertEquals(u1, this.userService.login("user", "password"));
	}
	
}
