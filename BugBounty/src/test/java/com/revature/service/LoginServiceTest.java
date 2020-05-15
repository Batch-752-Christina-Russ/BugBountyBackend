package com.revature.service;

import org.mockito.Mock;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class LoginServiceTest {
	
	@Mock
	UserService userService;
	
	@BeforeSuite
	public void setUp() {
		userService = new UserService();
	}
	
	@Test
	public void testLogin() {
		
		Assert.fail("String");
	}
	
}
