package com.revature.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer>{

	<S extends User> S save(User user);
	User findByUsername(String username);


	List<User> findFirst10ByOrderByPointsDesc();

	User findByUsernameAndPassword(String username, String password);
	
	
	
	public final static String GetRanked = "with ordered as (select username, points, "
			+ "rank () over (order by points desc) ranks from users) select ranks, username, points from ordered where username =?1";
	
	/**
	* Returns an object that contains the user's rank in the leader board, name, and points. 
	* <p>
	* This method is the called by the userService and is used for getting a single user's rank in the leader board. 
	* Uses a nativeQuery GetRanked which reads as the following: "with ordered as (select username, points,
	* rank () over (order by points desc) ranks from users) select ranks, username, points from ordered where username =?1"
	* Basically we want to order the table of user by their points, assign them a rank in that order, then select the
	* user that was passed in and retrieve their rank, username, and points fields.
	*
	* @param username name of the user that the requester want the rank of.
	* @return      Returns an object that contains a user's rank in the leader board, name, and points. 
	*/
	@Query(value=GetRanked, nativeQuery = true)
	public Object getRankByUserNameOrderByPoints(String username);


}
