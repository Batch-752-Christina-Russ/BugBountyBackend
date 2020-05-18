package com.revature.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer>{

	//<S extends User> S save(User user);
	User findByUsername(String username);

	User findByUsernameAndPassword(String username, String password);

	List<User> findFirst10ByOrderByPointsDesc();


}
