package com.revature.service;

import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
	
	public boolean saveBugReport(BugReport bugReport) {
		// checks if bugReport is a null value before trying to save it
		Optional<BugReport> bug = Optional.ofNullable(bugReport);
		if( bug.isPresent()) {
			this.bugReportRepository.save(bugReport);
			return true;
		} else return false;
	}
	
	public void deleteBugReport(int id) {
		this.bugReportRepository.deleteById(id);
	}

	public int sumBugReport(int id) {
		BugReport br = this.bugReportRepository.findById(id);
		return this.calculateTimePoints(br) + this.calculateSeverityPoints(br);
		
	}

	public int calculateSeverityPoints(BugReport br) {
		//   returns static values for Severity static values for severity low - 5, med - 15, high - 25, critical - 50
		switch(br.getSeverity()){
		case "low": return 5;
	    case "med":  return 15;
	    case "high": return 25;
	    case "critical":  return 50;
	    default: return 0;
		}
	}
	

	public boolean resolve(int id, String username) {
		//boolean to check status
		boolean worked = false;
		
		//get bug report to update
		BugReport report = this.findById(id);
		if(report == null) {
			return worked;
		}
		
		//get resolver's user object
		User resolver = this.userRepository.findByUsername(username);
		
		//update status and resolver
		report.setStatus("resolved");
		report.setResolver(resolver);
		
		//update record in database
		worked = this.saveBugReport(report);
		
		if(worked) {
			//update resolver's points
			int sum = this.sumBugReport(id);
			int currentpoints = resolver.getPoints();
			resolver.setPoints(currentpoints + sum);
			this.userRepository.save(resolver);
		}
		
		return worked;
	}

	public int calculateTimePoints(BugReport bugReportToCheck) {
		Calendar calendar = Calendar.getInstance(); 
		Date localDate = calendar.getTime();
		long daysBetween = ChronoUnit.DAYS.between(bugReportToCheck.getDate().toInstant(), localDate.toInstant());
		return (int) daysBetween;
	}
}
