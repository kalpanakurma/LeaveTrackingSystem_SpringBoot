package com.example.LeaveManagementSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.LeaveManagementSystem.entity.LeaveSummary;
import com.example.LeaveManagementSystem.service.LeaveSummaryService;


@RestController
@RequestMapping("/api/leave")
@CrossOrigin("*")
public class LeaveSummaryController {
	@Autowired
	private LeaveSummaryService leaveSummaryService;
	
	@GetMapping("/leaveSummary")
	public List<LeaveSummary> getAllLeaveSummaries() {
		// TODO Auto-generated method stub
		return leaveSummaryService.getAllLeaveSummaries();
	}

	
}
