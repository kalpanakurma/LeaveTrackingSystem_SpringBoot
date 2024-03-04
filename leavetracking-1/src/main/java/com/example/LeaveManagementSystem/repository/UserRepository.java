package com.example.LeaveManagementSystem.repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.LeaveManagementSystem.entity.Users;

public interface UserRepository  extends JpaRepository<Users,Long>{

	Optional<Users> findByEmail(String email);
    
	@Query(value = "SELECT id,email FROM user WHERE role = ?1", nativeQuery = true)
    List<Long> findAllManagerIdsByRole(String role);
	
	// to get all the managers details
	@Query(value = "SELECT * FROM user WHERE role = ?1", nativeQuery = true)
	 List<Users> findByRole(String role);
}
