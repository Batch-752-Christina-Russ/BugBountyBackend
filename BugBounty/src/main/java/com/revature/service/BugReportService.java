package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.BugReport;
import com.revature.model.User;
import com.revature.repository.BugReportRepository;
import com.revature.repository.UserRepository;

@Service("bugReportService")
public class BugReportService {

	@Autowired
	private BugReportRepository bugReportRepository;
	@Autowired
	private UserRepository userRepository;
	
	public List<BugReport> findAllBugReports(){
		return this.bugReportRepository.findAll();
	}
	
	public BugReport findById(int id) {
		return this.bugReportRepository.findById(id);
	}
	
	public void saveBugReport(BugReport bugReport) {
		this.bugReportRepository.save(bugReport);
	}
	
	public void deleteBugReport(int id) {
		this.bugReportRepository.deleteById(id);
	}
	
	public void resolve(int id, String username) {
		//get bug report to update
		BugReport report = this.findById(id);
		
		//get resolver's user object
		User resolver = this.userRepository.findByUsername(username);
		
		//update status and resolver
		report.setStatus("resolved");
		report.setResolver(resolver);
		
		//update record in database
		this.saveBugReport(report);
		
		//update resolver's points
		int sum = this.sumBugReport(id);
		int currentpoints = resolver.getPoints();
		resolver.setPoints(currentpoints + sum);
	}
}
