package com.example.LeaveManagementSystem.serviceImpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LeaveManagementSystem.entity.LeaveSummary;
import com.example.LeaveManagementSystem.repository.LeaveSummaryRepo;
import com.example.LeaveManagementSystem.service.LeaveSummaryService;
//import com.leavemanagement.LeaveManagement.services.LeaveTypeService;


@Service
public class LeaveSummaryServiceImpl implements LeaveSummaryService{
	@Autowired
	private LeaveSummaryRepo leavesummaryrepo;
	
	@Override
	public List<LeaveSummary> getAllLeaveSummaries() {
		// TODO Auto-generated method stub
		return leavesummaryrepo.getAllLeaveSummaries();
	}

}