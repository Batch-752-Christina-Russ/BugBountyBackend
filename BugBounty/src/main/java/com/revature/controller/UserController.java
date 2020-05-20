package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.User;
import com.revature.service.UserService;

@CrossOrigin
@RestController("userController")
@RequestMapping(path = "/user")
public class UserController {

	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping(path="/test", produces = MediaType.TEXT_HTML_VALUE)
	public String test() {
		return ("Hello Devin");
	}
	
	@GetMapping(path="/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAllUsers() {
		return this.userService.getAllUsers();
	}
	
	@PostMapping(path="/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void saveUser(@RequestBody User user) {
		this.userService.saveUser(user);
	}

	@GetMapping(path="/topten", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getTopTen()
	{
		return this.userService.getTopTen();
  }
  
	@PostMapping(path="/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public User login(@RequestBody User u) {
		u = this.userService.login(u.getUsername(), u.getPassword());
		u.setPassword(null);
		return u;
	}
	
	/**
	* Returns a Json object that contains the user's rank in the leader board, name, and points. 
	* <p>
	* This method is the end point for getting a single user's rank in the leader board.
	*
	* @param  name of the user that the requester wants the rank of.
	* @return      a Response Entity with a json body that contains a user's rank in the leader board, name, and points. 
	*/
	@GetMapping(path="/userrank/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getRankAndUser(@PathVariable String username) {
		return new ResponseEntity<>(this.userService.getRankAndUser(username), HttpStatus.OK);
	}
}
