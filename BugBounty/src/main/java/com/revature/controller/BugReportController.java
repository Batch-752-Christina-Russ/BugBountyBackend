package com.revature.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.BugReport;
import com.revature.model.User;
import com.revature.service.BugReportService;
import com.revature.service.UserService;

@CrossOrigin
@RestController("bugReportController")
@RequestMapping(path="/bugreport")
public class BugReportController {

	@Autowired
	private BugReportService bugReportService;

	@Autowired
	private UserService userService;

	/**
	* Returns an ArrayList of all BugReports on the database that have a pending status.
	* @return ArrayList of BugReports with Pending Status
	*/
	@GetMapping(path="/pending", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<BugReport> getAllPendingBugReports() {
		
		List<BugReport> reports = this.bugReportService.findByStatus("pending");
		return reports;
	}
	
	/**
	* Returns a Json object that approves or denies BugReports on the databse. 
	* <p>
	* This method is the end point for approving or denying Bugreports for people to resolve. Approving the Bugreport will
	* update the report with an open status, allowing other users to examine it while denying the BugReport will delete it completely
	* from the database.
	* @param bugReport BugReport to be approved or Denied
	*/
	@PostMapping(path="/approvedeny", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void saveUser(@RequestBody BugReport bugReport) {
				
		if(bugReport.getStatus().contentEquals("open")) {
			this.bugReportService.updateBugReportStatus(bugReport);
		} else if(bugReport.getStatus().contentEquals("delete")){
			this.bugReportService.deleteBugReport(bugReport.getId());
		}
	}
	
	/**
	* Returns a Json object that contains the BugReport to be saved onto database. 
	* <p>
	* This method is the end point for saving Bug Reports to the Database
	* @param bugReport BugReport to be saved onto database 
	* @return      a Response Entity with a json body that contains a BugReports and an approved HttpStatus 
	*/
	@PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BugReport> saveBugReport(@RequestBody BugReport bugReport) {
		User reporter = this.userService.findUserByUsername(bugReport.getReporter().getUsername());
		bugReport.setReporter(reporter);
		this.bugReportService.saveBugReport(bugReport);
		
		return new ResponseEntity<>(bugReport, HttpStatus.OK);
	}
	
	//need clarification on obj parameter use in this method
	/**
	* Returns a Json object that contains the BugReports Status open. 
	* <p>
	* This method is the end point for resolving bugs and ensuring that the creator and resolver of a bug report are different users.
	* @param obj 
	* @return      a Response Entity with a json body that contains a BugReports with the status of open . 
	*/
	@PostMapping(path ="/resolve", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> resolution(@RequestBody Object obj) {
		
		int id = (Integer) ((Map)obj).get("id");
		
		String username = (String) ((Map)obj).get("username");
		
		this.bugReportService.resolve(id, username);
		
		return new ResponseEntity<>(obj, HttpStatus.OK);
		
	}
	
	/**
	* Returns a Json object that contains the BugReports Status open. 
	* <p>
	* This method is the end point for getting a Bug Reports's with the Status open.
	*
	* @return      a Response Entity with a json body that contains a BugReports with the status value open . 
	*/

	@GetMapping(path="/open", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getBugReportByStatus() {
		return new ResponseEntity<>(this.bugReportService.findByStatus("open"), HttpStatus.OK);
	}
	
}
