package com.revature.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.revature.model.Role;
import com.revature.model.User;
import com.revature.repository.UserRepository;



public class TopTenServiceTest extends AbstractTestNGSpringContextTests{

	@Mock
	UserRepository userRepository;
	
	@InjectMocks
	UserService userService;
	
	//global variables
	Role role = new Role(1, "user");
	List<User> top10 = new ArrayList<User>();
	
	@BeforeSuite
	public void setup() {
		//get test objects
		userService = new UserService();
		
		top10.add(new User(10, "Not the right user", "password", 100, role));
		top10.add(new User(1, "not him either", "password", 90, role));
	    top10.add(new User(2, "not her", "password", 80, role));
	    top10.add(new User(3, "Still the wrong guy", "password", 70, role));
	    top10.add(new User(4, "Worng User", "password", 60, role));
	    top10.add(new User(5, "IAmTheRightTestUser", "password", 50, role));
	    top10.add(new User(6, "Bob", "password", 40, role));
	    top10.add(new User(7, "Joebob", "password", 30, role));
	    top10.add(new User(8, "Jill", "password", 20, role));
	    top10.add(new User(9, "TooManyUsers", "password", 10, role));
	   
		
		//initialize Mockito
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetTopTen() {
		//TDD, methods do not yet exist
		System.out.println(top10);
		when(userRepository.findFirst10ByOrderByPointsDesc()).thenReturn(top10);
		
		//return a list of Users
		Assert.assertEquals(top10, userService.getTopTen());
		
		//should be no more than 10 users
		Assert.assertEquals(10, top10.size());
		
		//should be ordered by rank
		Assert.assertEquals(true, top10.get(0).getPoints() > top10.get(1).getPoints());
		
	}
}
