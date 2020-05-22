package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {

	@Id
	@SequenceGenerator(name="users_user_id_seq", allocationSize = 1)
	@GeneratedValue(generator="users_user_id_seq", strategy = GenerationType.AUTO)
	@Column(name="user_id")
	private int id;
	@Column(unique=true, name="username")
	private String username;
	@Column(name="password")
	private String password;
	@Column(name="points")
	private int points;
	@ManyToOne
	@JoinColumn(name="role_id")
	private Role role;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	/**
	* Converts User object values into Hashcode
	* <p>
	* This method is used to convert the values of the selected User into Hash Code and returns those values as an int.
	*
	* @return returns int value from hashcode
	* 
	*/
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + points;
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
	
	/**
	* Returns a boolean if the instance of User equals in value to the parameter entered
	* <p>
	* This method is used to check if the instance of User is equal to object and checking if the obj is null.
	* 
	* @param  obj  object that is to be compared to instance of User class
	* @return true if Role equals parameter, obj is not null, and value of User equals value of parameter,
	* 
	*/
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (points != other.points)
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	/**
	* Converts User object into a String
	* <p>
	* This method overrides the toString method to output proper format for webpages.
	*
	* @return User instance as a String.
	* 
	*/
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", points=" + points + ", role="
				+ role + "]";
	}

	

	public User(int id, String username, String password, int points, Role role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.points = points;
		this.role = role;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
