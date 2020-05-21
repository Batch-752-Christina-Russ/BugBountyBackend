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
	
	public void updateBugReportStatus(BugReport bugReport) {
		//this.bugReportRepository.save(bugReport);
		this.bugReportRepository.updateStatus(bugReport.getId(), bugReport.getStatus());
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
		
		//verify that points are NOT added if bug was already marked resolved
		if(report.getStatus().toLowerCase().equals("resolved")) return worked;
		
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

	/**
	* Returns an int equaling the time bonus points of a resolved bug, one point per day since submission. 
	* <p>
	* This method gets a Calendar object to find out the current day, finds out the number of days 
	* that have passed between the bugs submission date and the current day. Then cast that as 
	* an int which represents the number of points to return. Right now one point is rewarded per day.
	*
	* @param  a bug report that has just been resolved. It will use it's submit date.
	* @return      an int equaling the point value of a resolved bug time bonus.
	*/
	public int calculateTimePoints(BugReport bugReportToCheck) {
		Calendar calendar = Calendar.getInstance(); 
		Date localDate = calendar.getTime();
		long daysBetween = ChronoUnit.DAYS.between(bugReportToCheck.getDate().toInstant(), localDate.toInstant());
		return (int) daysBetween;
	}
	
	public List<BugReport> findByStatus(String status){
		List<BugReport> bugreports = this.bugReportRepository.findAllByStatus(status);
		for(BugReport b : bugreports) {
			switch(status.toLowerCase()){
			case "pending": 
				b.getReporter().setPassword(null);
				break;
			case "open":
				b.getReporter().setPassword(null);
				break;
			case "resolved":
				b.getReporter().setPassword(null);
				b.getResolver().setPassword(null);
				break;
				default:;
			}
		}
		return bugreports;
	}
}
