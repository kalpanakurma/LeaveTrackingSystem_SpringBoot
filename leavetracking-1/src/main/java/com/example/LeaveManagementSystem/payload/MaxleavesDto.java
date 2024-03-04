package com.example.LeaveManagementSystem.payload;

import lombok.Data;

@Data

public class MaxleavesDto {
    
	private Long id;
	private String leavetype;
	private Long maxleaves;
}
