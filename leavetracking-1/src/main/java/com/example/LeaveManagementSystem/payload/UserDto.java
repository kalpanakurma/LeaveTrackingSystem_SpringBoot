package com.example.LeaveManagementSystem.payload;

import java.sql.Date;
import java.util.Objects;

import lombok.Data;

@Data

public class UserDto {
	private Long id;
	private String name;
	private String email;
	private String password;
	private String personalEmail;
	private String mobileNo;
	private Date dateOfJoining;
	private Long managerId;
}
