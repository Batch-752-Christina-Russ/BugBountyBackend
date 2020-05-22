package com.revature.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.model.BugReport;

@Repository("bugReportRepository")
public interface BugReportRepository extends JpaRepository<BugReport, Integer>{

	List<BugReport> findAll();
	BugReport findById(int id);
	<S extends BugReport> S save (BugReport bugReport);
	void deleteById(int id);
	
	
	/**
	* Updates a BugReport status where the BugReport has a specific id. 
	* <p>
	* This method is the called by the userService and is used for updating a single BugReports's status in the database. 
	* Uses a nativeQuery GetBugReport which reads as the following: 
	* "Update BugReport set status=?  where id = ?"
	* This allows for updating status of a report for saving, deleting, and resolving.
	*
	* @param  name of the user that the requester want the rank of.
	* @return      Returns an object that contains a user's rank in the leader board, name, and points. 
	*/
	@Transactional
	@Modifying
	@Query("UPDATE BugReport SET status=:status WHERE id=:id")
	int updateStatus(@Param("id") int id, @Param("status") String status);
	//Integer sumBugReport(int id);
	List<BugReport> findAllByStatus(String status);
}
