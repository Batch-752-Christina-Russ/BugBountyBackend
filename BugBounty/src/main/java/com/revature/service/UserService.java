package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.User;
import com.revature.repository.UserRepository;

@Service("userService")
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUsers(){
		return this.userRepository.findAll();
	}
	
	public void saveUser(User user) {
		this.userRepository.save(user);
	}

	public List<User> getTopTen() {
		List<User> topTen =  this.userRepository.findFirst10ByOrderByPointsDesc();
		
		//nullify passwords
		for(User u : topTen) {
			u.setPassword(null);
		}
		
		return topTen;
	}
	
	public User login(String username, String password) {
		return this.userRepository.findByUsernameAndPassword(username, password);
	}

	public Object getRankAndUser(String username) {
		return this.userRepository.getRankByUserNameOrderByPoints(username);
	}
	
	public User findUserByUsername(String username) {
		return this.userRepository.findByUsername(username);
	}
}

