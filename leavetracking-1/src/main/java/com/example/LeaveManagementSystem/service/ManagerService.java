package com.example.LeaveManagementSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.LeaveManagementSystem.entity.Users;
import com.example.LeaveManagementSystem.payload.ManagerDto;
import com.example.LeaveManagementSystem.payload.UserDto;
import com.example.LeaveManagementSystem.repository.UserRepository;

public interface ManagerService {
	public UserDto createManager(UserDto userDto);
    

	    public List<Long> getAllManagerIds();
	    
	    public List<Users> getAllManagers();

}
