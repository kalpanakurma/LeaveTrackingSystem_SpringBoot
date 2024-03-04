package com.example.LeaveManagementSystem.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.LeaveManagementSystem.entity.LeaveSummaryFiltered;
import com.example.LeaveManagementSystem.service.LeaveSummaryFilteredService;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class LeaveSummaryFilteredController {
	@Autowired
	private LeaveSummaryFilteredService leaveSummaryService;
	
	@GetMapping("/leaves/filtered")
    public List<LeaveSummaryFiltered> getLeaveSummaryFiltered(@RequestParam String fromDate, @RequestParam String toDate) {
        return leaveSummaryService.getLeaveSummaryFiltered(fromDate, toDate);
    }
}
