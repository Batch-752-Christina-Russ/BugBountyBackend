package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import com.revature.model.BugReport;
import com.revature.model.User;
import com.revature.service.BugReportService;

@CrossOrigin
@Controller
@RequestMapping(path="/bugreport")
public class BugReportController {

	@Autowired
	private BugReportService bugReportService;
	

	@PostMapping("/resolve/{id}/{username}")
	public void resolution(@PathVariable int id, @PathVariable String username) {
		this.bugReportService.resolve(id, username);
	}

}
