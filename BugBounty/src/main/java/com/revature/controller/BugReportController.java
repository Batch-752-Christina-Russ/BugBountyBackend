package com.revature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.revature.service.BugReportService;

@CrossOrigin
@Controller
@RequestMapping(path="/bugreport")
public class BugReportController {

	@Autowired
	private BugReportService bugReportService;
	
	@GetMapping(path="/status/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getBugReportByStatus(@PathVariable String status) {
		return new ResponseEntity<>(this.bugReportService.findByStatus(status), HttpStatus.OK);
	}
}
