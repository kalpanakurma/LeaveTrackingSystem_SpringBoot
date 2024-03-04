package com.example.LeaveManagementSystem.service;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.LeaveManagementSystem.entity.LeaveSummaryFiltered;


@Service
public interface LeaveSummaryFilteredService {
	List<LeaveSummaryFiltered> getLeaveSummaryFiltered(String fromDate, String toDate);
}