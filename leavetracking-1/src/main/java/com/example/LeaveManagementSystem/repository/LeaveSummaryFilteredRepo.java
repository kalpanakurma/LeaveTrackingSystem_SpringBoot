package com.example.LeaveManagementSystem.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.LeaveManagementSystem.entity.LeaveSummaryFiltered;


@Repository
public interface LeaveSummaryFilteredRepo extends JpaRepository<LeaveSummaryFiltered, Long>{

	@Query(value = "CALL leave_summary_filtered(?1, ?2)", nativeQuery = true)
    List<LeaveSummaryFiltered> getLeaveSummaryFiltered(String fromDate, String toDate);
}