package com.example.LeaveManagementSystem.service;

import com.example.LeaveManagementSystem.payload.UserDto;

public interface UserService {
	
	public  UserDto createEmployee(UserDto userDto);

	public boolean isUserWithEmailExists(String email);
	public  boolean isManager(String email);
}
