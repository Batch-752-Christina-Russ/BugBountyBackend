package com.revature.service;

import java.time.Duration;
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

	public int calculateTimePoints(BugReport bugReportToCheck) {
		Calendar.Builder calendarBuilder = new Calendar.Builder();
		Calendar calendar = calendarBuilder.build();
		Date localDate = calendar.getTime();
		long daysBetween = ChronoUnit.DAYS.between(bugReportToCheck.getDate().toInstant(), localDate.toInstant());
		System.out.println(daysBetween);
		return (int) daysBetween;

	}

	public Integer sumBugReport(int id) {
		BugReport br = this.bugReportRepository.findById(id);
		return this.bugReportService.calculateTimePoints(br) + this.bugReportService.calculateSeverityPoints(br);
	}
}
