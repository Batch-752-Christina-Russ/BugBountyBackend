package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.BugReport;
import com.revature.service.BugReportService;

@CrossOrigin
@RestController("bugReportController")
@RequestMapping(path="/bugreport")
public class BugReportController {

	@Autowired
	private BugReportService bugReportService;
	
	@GetMapping(path="/pending", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<BugReport> getAllPendingBugReports() {
		
		List<BugReport> reports = this.bugReportService.findByStatus("pending");
		return reports;
	}
	
	@PostMapping(path="/approvedeny", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void saveUser(@RequestBody BugReport bugReport) {
				
		if(bugReport.getStatus().contentEquals("open")) {
			this.bugReportService.updateBugReportStatus(bugReport);
		} else if(bugReport.getStatus().contentEquals("delete")){
			this.bugReportService.deleteBugReport(bugReport.getId());
		}
	}
	
	@PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BugReport> saveBugReport(@RequestBody BugReport bugReport) {
		this.bugReportService.saveBugReport(bugReport);
		return new ResponseEntity<>(bugReport, HttpStatus.OK);
	}
	
	@GetMapping(path = "/delete/{id}")
	public void deleteBugReport(@PathVariable int id) {
		this.bugReportService.deleteBugReport(id);
	}
  
	@PostMapping("/resolve/{id}/{username}")
	public void resolution(@PathVariable int id, @PathVariable String username) {
		this.bugReportService.resolve(id, username);
	}

	@GetMapping(path="/open", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getBugReportByStatus() {
		return new ResponseEntity<>(this.bugReportService.findByStatus("open"), HttpStatus.OK);
	}
	
	
	
}
