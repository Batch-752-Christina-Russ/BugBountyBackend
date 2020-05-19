package com.revature.service;

import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
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

	public Integer sumBugReport(int id) {
		BugReport br = this.bugReportRepository.findById(id);
		return this.calculateTimePoints(br) + this.calculateSeverityPoints(br);
	}

	private int calculateSeverityPoints(BugReport br) {
		//   returns static values for Severity static values for severity low - 5, med - 15, high - 25, critical - 50
		switch(br.getSeverity()){
		case "low": return 5;
	    case "med":  return 15;
	    case "high": return 25;
	    case "critical":  return 50;
	    default: return 0;
		}
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

	public int calculateTimePoints(BugReport bugReportToCheck) {
		Calendar calendar = Calendar.getInstance(); 
		Date localDate = calendar.getTime();
		long daysBetween = ChronoUnit.DAYS.between(bugReportToCheck.getDate().toInstant(), localDate.toInstant());
		System.out.println(daysBetween);
		System.out.println(bugReportToCheck.getDate().toInstant());
		System.out.println(localDate.toInstant());
		return (int) daysBetween;
	}

	public int sumBugReport(int id) {
		BugReport br = this.bugReportRepository.findById(id);
		return this.bugReportService.calculateTimePoints(br) + this.bugReportService.calculateSeverityPoints(br);
	}
}
