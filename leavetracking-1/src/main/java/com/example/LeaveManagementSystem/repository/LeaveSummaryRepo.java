package com.example.LeaveManagementSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.LeaveManagementSystem.entity.LeaveSummary;


@Repository
public interface LeaveSummaryRepo extends JpaRepository<LeaveSummary, Long>{

	@Query("select u from LeaveSummary u")
	List<LeaveSummary> getAllLeaveSummaries();
}
