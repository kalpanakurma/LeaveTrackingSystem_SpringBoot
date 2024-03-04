package com.example.LeaveManagementSystem.entity;

import java.sql.Date;

import com.example.LeaveManagementSystem.payload.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="user")
public class Users {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name", nullable=false)
	private String name;
	
	@Column(name="email", nullable=false)
	private String email;
	
	@Column(name="password", nullable=false)
	private String password;
	
	@Column(name="personal_email", nullable=false)
	private String personalEmail;
	
	@Column(name="mobile_no", nullable=false)
	private String mobileNo;
	
	@Column(name="date_of_joining", nullable=false)
	private Date dateOfJoining ;
	
	@Column(name="manager_id")
	private Long managerId ;
	
	@Enumerated(EnumType.STRING)
	private Role role;
}
