package com.revature.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.BugReport;
import com.revature.repository.BugReportRepository;

@Service("bugReportService")
public class BugReportService {

	@Autowired
	private BugReportRepository bugReportRepository;
	
	public List<BugReport> findAllBugReports(){
		return this.bugReportRepository.findAll();
	}
	
	public BugReport findById(int id) {
		return this.bugReportRepository.findById(id);
	}
	
	public boolean saveBugReport(BugReport bugReport) {
		Optional<BugReport> bug = Optional.ofNullable(this.bugReportRepository.save(bugReport));
		if( bug.isPresent()) {
			return true;
		} else return false;
	}
	
	public void deleteBugReport(int id) {
		this.bugReportRepository.deleteById(id);
	}
}
