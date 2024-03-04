package com.example.LeaveManagementSystem.payload;

import lombok.Data;

@Data

public class LeaveSummaryDto {

    private Long sNo;
    private String employee;
    private int noOfLeavesApproved;
    private int noOfLeavesRejected;
    private int noOfLeavesPending;

    // Getters and setters
}

