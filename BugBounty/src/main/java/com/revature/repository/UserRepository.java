package com.revature.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.model.User;
//merge fixin
@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer>{

	//<S extends User> S save(User user);
	User findByUsername(String username);

	List<User> findFirst10ByOrderByPointsDesc();

	User findByUsernameAndPassword(String username, String password);
	
	public final static String GetRanked = "with ordered as (select username, points, "
			+ "rank () over (order by points desc) ranks from users) select ranks, username, points from ordered where username =?1";
	
	@Query(value=GetRanked, nativeQuery = true)
	public Object getRankByUserNameOrderByPoints(String username);

}
