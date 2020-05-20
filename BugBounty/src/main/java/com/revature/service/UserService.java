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
		return this.userRepository.findFirst10ByOrderByPointsDesc();
	}
	
	public User login(String username, String password) {
		return this.userRepository.findByUsernameAndPassword(username, password);
	}

	/**
	* Returns an object that contains the user's rank in the leader board, name, and points. 
	* <p>
	* This method is the called by the end point for getting a single user's rank in the leader board.
	*
	* @param  name of the user that the requester want the rank of.
	* @return      Returns an object that contains a user's rank in the leader board, name, and points. 
	*/
	public Object getRankAndUser(String username) {
	return this.userRepository.getRankByUserNameOrderByPoints(username);
}

}
