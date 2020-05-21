package com.revature.service;


import static org.testng.Assert.assertEquals;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.repository.UserRepository;

@SpringBootTest
class LeaderBoardUserRankService {
	
	@Mock
	UserRepository userRepository;

	@InjectMocks
	UserService userService;
	
	//setting up a mock object that will represent a user
	Object object;
	
	@BeforeTest
	public void setUp() {
		userService = new UserService();
		object = new Object();
		MockitoAnnotations.initMocks(this);
	}
	
	// Should return a user and it's rank in the leader board as a generic object.
	@Test
	void testLeaderBoardRank() {
		// mocking getRankByUserNameOrderByPoints, it return a generic object.
		Mockito.when(userRepository.getRankByUserNameOrderByPoints("IAmTheRightTestUser")).thenReturn(object);
		
		// check that the service layer is passing a generic object.
		assertEquals(object, this.userService.getRankAndUser("IAmTheRightTestUser"));
	}
}
