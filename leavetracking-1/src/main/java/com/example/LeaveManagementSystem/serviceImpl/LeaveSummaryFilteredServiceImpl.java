package com.example.LeaveManagementSystem.serviceImpl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LeaveManagementSystem.entity.LeaveSummaryFiltered;
import com.example.LeaveManagementSystem.repository.LeaveSummaryFilteredRepo;
import com.example.LeaveManagementSystem.service.LeaveSummaryFilteredService;


@Service
public class LeaveSummaryFilteredServiceImpl implements LeaveSummaryFilteredService{
	@Autowired
	private LeaveSummaryFilteredRepo leaverepo;
	
	@Override
	public List<LeaveSummaryFiltered> getLeaveSummaryFiltered(String fromDate, String toDate) {
		// TODO Auto-generated method stub
		return leaverepo.getLeaveSummaryFiltered(fromDate, toDate);
	}

}