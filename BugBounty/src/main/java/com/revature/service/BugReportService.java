package com.revature.service;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
	

	
	public int calculateTimePoints(BugReport bugReportToCheck) {
		Calendar.Builder calendarBuilder = new Calendar.Builder();
		Calendar calendar = calendarBuilder.build();
		Date localDate = calendar.getTime();
		long daysBetween = ChronoUnit.DAYS.between(bugReportToCheck.getDate().toInstant(), localDate.toInstant());
		System.out.println(daysBetween);
		return (int) daysBetween;

	}
}
