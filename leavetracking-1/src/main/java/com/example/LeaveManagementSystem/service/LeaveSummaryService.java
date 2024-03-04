package com.example.LeaveManagementSystem.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.LeaveManagementSystem.entity.LeaveSummary;

@Service
public interface LeaveSummaryService {
	List<LeaveSummary> getAllLeaveSummaries();
}
