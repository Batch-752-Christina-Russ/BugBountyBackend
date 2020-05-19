package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
		return this.bugReportService.findByStatus("pending");
	}
	
	@PostMapping(path="/approvedeny", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void saveUser(@RequestBody BugReport bugReport) {
		if(bugReport.getStatus().contentEquals("approved")) {
			this.bugReportService.saveBugReport(bugReport);
		} else if(bugReport.getStatus().contentEquals("denied")){
			this.bugReportService.deleteBugReport(bugReport.getId());
		}
	}
}
