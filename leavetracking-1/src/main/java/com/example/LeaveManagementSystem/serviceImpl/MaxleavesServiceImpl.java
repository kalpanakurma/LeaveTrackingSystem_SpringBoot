package com.example.LeaveManagementSystem.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.LeaveManagementSystem.entity.MaxLeaves;
import com.example.LeaveManagementSystem.entity.Users;
import com.example.LeaveManagementSystem.payload.MaxleavesDto;
import com.example.LeaveManagementSystem.payload.Role;
import com.example.LeaveManagementSystem.payload.UserDto;
import com.example.LeaveManagementSystem.repository.MaxleavesRepository;
import com.example.LeaveManagementSystem.repository.UserRepository;
import com.example.LeaveManagementSystem.service.MaxleavesService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class MaxleavesServiceImpl implements MaxleavesService {

    public static final Logger logger = LoggerFactory.getLogger(MaxleavesServiceImpl.class);

    @Autowired
    private MaxleavesRepository maxleavesRepository;

    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    
    private UserRepository userRepository;

   
    @Override
    public MaxleavesDto createMaxleaves(MaxleavesDto maxleavesDto) {
        logger.info("Creating leavetype: {}", maxleavesDto);
        
       MaxLeaves leavetype = modelMapper.map(maxleavesDto, MaxLeaves.class);
        logger.debug("Saving leavetype: {}", leavetype);
        MaxLeaves savedLeavetype = maxleavesRepository.save(leavetype);
        logger.info("Employee created: {}", savedLeavetype);
        return modelMapper.map(savedLeavetype, MaxleavesDto.class);
    }
	@Override
	public boolean isLeaveTypeExists(String leavetype) {
		 Optional<MaxLeaves> user = maxleavesRepository.findByLeavetype(leavetype);
	        return user.isPresent();
	}
	@Override
	public List<MaxLeaves> getAllMaxLeaves() {
        return maxleavesRepository.findAll();
    }
	 @Transactional
	    public void updateMaxLeavesAttribute(Long id, Long newMaxLeaves) {
	        MaxLeaves maxLeaves = maxleavesRepository.findById(id)
	                .orElseThrow(() -> new EntityNotFoundException("MaxLeaves not found with id: " + id));

	        maxLeaves.setMaxleavesLong(newMaxLeaves);
	        maxleavesRepository.save(maxLeaves);
	    }
}
