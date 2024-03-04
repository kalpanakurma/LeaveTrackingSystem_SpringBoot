package com.example.LeaveManagementSystem.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;


import com.example.LeaveManagementSystem.entity.MaxLeaves;

import com.example.LeaveManagementSystem.payload.MaxleavesDto;

public interface MaxleavesService {
	public  MaxleavesDto createMaxleaves(MaxleavesDto maxleavesDto);
	
	public boolean isLeaveTypeExists(String leavetype);
	public List<MaxLeaves> getAllMaxLeaves() ;

	public void updateMaxLeavesAttribute(Long id, Long newMaxLeaves);
}

