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
	
	//need help with what the update is used for
	/**
	* Updates a BugReport status where the BugReport has a specific id. 
	* <p>
	* This method is the called by the BugReportService and is used for updating a single BugReports's status in the database. 
	* Uses a nativeQuery GetBugReport which reads as the following: 
	* "Update BugReport set status=?  where id = ?"
	* This allows for updating status of a report for resolved and approved.
	*
	* @param  id int value for identifying the BugReport to be updated
	* @param status String value to set the BugReports Status value to.
	* @return      returns an int value 
	*/
	@Transactional
	@Modifying
	@Query("UPDATE BugReport SET status=:status WHERE id=:id")
	int updateStatus(@Param("id") int id, @Param("status") String status);
	//Integer sumBugReport(int id);
	List<BugReport> findAllByStatus(String status);
}
