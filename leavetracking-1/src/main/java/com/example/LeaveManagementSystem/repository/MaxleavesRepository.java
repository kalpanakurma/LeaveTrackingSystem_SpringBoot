package com.example.LeaveManagementSystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.LeaveManagementSystem.entity.MaxLeaves;

public interface MaxleavesRepository extends JpaRepository<MaxLeaves, Long>{

	
Optional<MaxLeaves> findByLeavetype(String leavetype);

}

