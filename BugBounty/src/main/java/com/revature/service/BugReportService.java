package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.BugReport;
import com.revature.model.User;
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
	
	public List<BugReport> findByStatus(String status){
		List<BugReport> bugreports = this.bugReportRepository.findAllByStatus(status);
		for(BugReport b : bugreports) {
			User reporter = b.getReporter();
			reporter.setPassword(null);
			User resolver = b.getResolver();
			resolver.setPassword(null);
			b.setReporter(reporter);
			b.setResolver(resolver);
		}
		return bugreports;
	}
}
