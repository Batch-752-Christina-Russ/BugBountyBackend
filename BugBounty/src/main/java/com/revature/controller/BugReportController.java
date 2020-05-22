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

	@GetMapping(path="/pending", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<BugReport> getAllPendingBugReports() {
		
		List<BugReport> reports = this.bugReportService.findByStatus("pending");
		return reports;
	}
	
	@PostMapping(path="/approvedeny", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void approveDenyBugReport(@RequestBody BugReport bugReport) {
				
		if(bugReport.getStatus().contentEquals("open")) {
			this.bugReportService.updateBugReportStatus(bugReport);
		} else if(bugReport.getStatus().contentEquals("delete")){
			this.bugReportService.deleteBugReport(bugReport.getId());
		}
	}
	
	@PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BugReport> saveBugReport(@RequestBody BugReport bugReport) {
		User reporter = this.userService.findUserByUsername(bugReport.getReporter().getUsername());
		bugReport.setReporter(reporter);
		this.bugReportService.saveBugReport(bugReport);
		
		return new ResponseEntity<>(bugReport, HttpStatus.OK);
	}
  
	@PostMapping(path ="/resolve", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> resolution(@RequestBody Object obj) {
		
		int id = (Integer) ((Map)obj).get("id");
		
		String username = (String) ((Map)obj).get("username");
		
		this.bugReportService.resolve(id, username);
		
		return new ResponseEntity<>(obj, HttpStatus.OK);
		
	}
	@GetMapping(path="/open", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<BugReport> getBugReportByStatus() {
		List<BugReport> reports = this.bugReportService.findByStatus("open");
		return reports;	
		}
	
}
