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
	/**
	 * <h1> getTopTen method</h1>
	* This the method getTopTen() from the database.
	* 
	* This method retrieves the 10 ten users from the database based on their point totals.
	* 
	* @author Colin Baldwin
	* @return List<User> the returned list of users for top ten.
	*/
	public List<User> getTopTen() {
		List<User> topTen =  this.userRepository.findFirst10ByOrderByPointsDesc();
		
		//nullify passwords
		for(User u : topTen) {
			u.setPassword(null);
		}
		
		return topTen;
	}
	
	/**
	 * <h1> Login method</h1>
	* This method Login allows users to login into their account.
	* 
	* By taking username and password, login finds the appropiate row if it exists allowing users to login.
	* 
	* @param username	user that is being looked up for login
	* @param password	password entered to see if it matches the user password
	* @return User the returned user if it exists
	* 
	*/
	
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
