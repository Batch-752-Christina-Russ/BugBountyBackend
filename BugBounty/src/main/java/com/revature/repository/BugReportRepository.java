package com.revature.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.BugReport;

@Repository("bugReportRepository")
public interface BugReportRepository extends JpaRepository<BugReport, Integer>{

	List<BugReport> findAll();
	BugReport findById(int id);
	//<S extends BugReport> S save (BugReport bugReport);
	void deleteById(int id);
	List<BugReport> findAllByStatus(String status);
}
