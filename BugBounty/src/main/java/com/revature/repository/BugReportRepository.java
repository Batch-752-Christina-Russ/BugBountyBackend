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
	//<S extends BugReport> S save (BugReport bugReport);
	void deleteById(int id);

	List<BugReport> findAllByStatus(String status);
	
	@Transactional
	@Modifying
	@Query("UPDATE BugReport SET status=:status WHERE id=:id")
	int updateStatus(@Param("id") int id, @Param("status") String status);
	Integer sumBugReport(int id);
}
