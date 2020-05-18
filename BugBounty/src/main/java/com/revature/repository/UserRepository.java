package com.revature.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.User;
//merge fixin
@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer>{

	<S extends User> S save(User user);
	User findByUsername(String username);
	List<User> findFirst10ByOrderByPointsDesc();

}
