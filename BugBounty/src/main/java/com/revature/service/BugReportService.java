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


	/**
	* Returns All the BugReport objects on Database.
	* <p>
	* This method returns all existing BugReports from the database.
	*
	* @return 			BugReport ArrayList of all BugReports that are in the Database.
	* 
	*/
	public List<BugReport> findAllBugReports(){
		return this.bugReportRepository.findAll();
	}
	
	/**
	* Returns a BugReport object from the database that matches the id.
	* <p>
	* This method returns a BugReport object from the database that has the matching id entered into the method parameter.
	*
	* @param id 		id of the BugReport that will be returned from the database
	* @return 			BugReport that matches id parameter
	* 
	*/

	public BugReport findById(int id) {
		return this.bugReportRepository.findById(id);
	}
	
	
	//NEED HELP ON THIS ONE
	/**
	* Checks if a BugReport has a Null value and if it is not null and is Present returns a boolean based on this check.
	* <p>
	* This method saves a new BugReport to the database, it checks if the values are null and if it passes it will save the
	* new report to the database.
	*
	* @param bugReport 		the BugReport that will be checked for in the database.
	* @return 				result of check if the BugReport is present it will return true
	* 
	*/
	
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
	/**
	* Deletes a BugReport from the database.
	* <p>
	* This method deletes a BugReport from the database that has an id that matches the inputed parameter.
	*
	* @param id int value used to find the report in the database and delete it
	* 
	*/
	
	public void deleteBugReport(int id) {
		this.bugReportRepository.deleteById(id);
	}
	
	/**
	* Returns an int for the total value of a BugReport.
	* <p>
	* This method calls the BugReportRepository to find a BugReport and calculate the total point value the completed
	* BugReport is worth. It does this by calculating the severity of the problem using calculateSeverityPoints(), 
	* the time it took to solve that problem using calculateTimePoints(), adding them together and returning the value as an
	* int.
	*
	* @param id int value used to find the report in the database and use that BugReport to calculate the return value
	* @return static int value obtained from adding the number of days the BugReport was needed to solve and the severity value.
	* 
	*/
	public int sumBugReport(int id) {
		BugReport br = this.bugReportRepository.findById(id);
		return this.calculateTimePoints(br) + this.calculateSeverityPoints(br);
		
	}
	
	/**
	* Calculates the Severity of a problem into an int for sumBugReport.
	* <p>
	* This method takes in a BugReport parameter br, taking the the string value of severity, it returns a value
	* based on the already established criteria from low to critical.  If there was no value or an imporper string
	* the method returns 0.
	*
	* @param  br BugReport object used to get the severity 
	* @return static int value based on severity string,  low - 5, med - 15, high - 25, critical - 50, else it will return 0
	* 
	*/

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
	

	/**
	* Updates the users current score when a new report is submitted.
	* <p>
	* This method is used after a new Bugreport is submitted, using id parameter to identify the bug report to be calculated
	*  and the sum of the points from the report are calculated,
	* with that value it adds it to the user with username that equals username parameter.
	*
	* @param  id  id for BugReport object used for getting sum value
	* @param username username of User to be used to calculate new score
	* 
	*/
	


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
			switch(status){
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
