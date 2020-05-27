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
	
	/**
	 * <h1> getAllUsers method</h1>
	* The method getAllUsers calls on the repository to retrieve a list of all users that exist on the database
	* 
	* @return List of ten users with the highest points score.
	*/
	public List<User> getAllUsers(){
		return this.userRepository.findAll();
	}
	/**
	 * <h1> Save User method</h1>
	* The method saves user to database, adding the value via UserRepository.
	* @param user user to be saved to database
	*/
	public void saveUser(User user) {
		this.userRepository.save(user);
	}
	/**
	 * <h1> getTopTen method</h1>
	* The method getTopTen retrieves the 10 ten users from the database based on their point totals.
	* 
	* @return  the returned list of users for top ten in and ArrayList.
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
	* By taking username and password, login finds the appropriate row from the database by calling UserRepository.
	* if the User exists, access to the user's information and actions will be granted.
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
	* @param username name of the user that the requester want the rank of.
	* @return      Returns an object that contains a user's rank in the leader board, name, and points. 
	*/
	public Object getRankAndUser(String username) {
		return this.userRepository.getRankByUserNameOrderByPoints(username);
	}
	
	/**
	 * <h1> Login method</h1>
	* This method returns a user with a specific username.
	* 
	* @param username	username that is being used to find user on database  
	* @return User the returned user if it exists
	* 
	*/
	
	public User findUserByUsername(String username) {
		return this.userRepository.findByUsername(username);
	}
}

