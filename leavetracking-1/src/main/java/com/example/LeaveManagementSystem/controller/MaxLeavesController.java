package com.example.LeaveManagementSystem.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.LeaveManagementSystem.entity.MaxLeaves;
import com.example.LeaveManagementSystem.payload.BackendResponse;

import com.example.LeaveManagementSystem.payload.MaxleavesDto;
import com.example.LeaveManagementSystem.service.MaxleavesService;

@RestController
@RequestMapping("/api/leave")
@CrossOrigin(origins = "*")
public class MaxLeavesController {
    
    private static final Logger logger = LoggerFactory.getLogger(MaxLeavesController.class);

    
    @Autowired
    private MaxleavesService maxleavesService;
    
    
    
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
   
    
    
    // Api for adding a leavetype    
    
    @PostMapping("/add/leavetype")
    public ResponseEntity<BackendResponse> addLeaveType(@RequestBody MaxleavesDto maxleavesDto){
    	BackendResponse response=new BackendResponse();  
    	
    	//checking the fields are empty
if (maxleavesDto == null || maxleavesDto.getLeavetype() == null || maxleavesDto.getLeavetype().isEmpty() || maxleavesDto.getMaxleaves() == null)
{

 StringBuilder errorMessage = new StringBuilder("Fields ");
                if (maxleavesDto.getLeavetype() == null || maxleavesDto.getLeavetype().isEmpty()) {
                    errorMessage.append("'leavetype', ");
                }
                if (maxleavesDto.getMaxleaves() == null) {
                    errorMessage.append("'maxleaves', ");
                }
                errorMessage.append("are mandatory");
                response.setMessage(errorMessage.toString());
                response.setStatus("fail");
                response.setData("empty");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
}
//checking that the leavetype exists already
if (maxleavesService.isLeaveTypeExists(maxleavesDto.getLeavetype())) {
    response.setMessage("leavetype already exists");
    response.setStatus("fail");
    response.setData("empty");
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
}
      //if no leavetype is present in db then create a employee
        MaxleavesDto createdLeavetype = maxleavesService.createMaxleaves(maxleavesDto);
        logger.info("Leave type added successfully: {}", createdLeavetype);
        response.setMessage("added  successfully");
		response.setStatus("success");
		response.setData(createdLeavetype);
        
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    // api to get all the maxleaves
    
    @RequestMapping("maxleaves")
        @GetMapping
        public List<MaxLeaves> getAllMaxLeaves() {
            return maxleavesService.getAllMaxLeaves();
        }

   // api to edit all the maxleaves number

@PutMapping("/set/{id}")
public ResponseEntity<BackendResponse> updateMaxLeavesAttribute(@PathVariable Long id, @RequestParam("newMaxLeaves") Long newMaxLeaves) {
    try {
    	maxleavesService.updateMaxLeavesAttribute(id, newMaxLeaves);
        BackendResponse response = new BackendResponse("Max leaves attribute updated successfully", "success", null);
        return ResponseEntity.ok().body(response);
    } catch (Exception e) {
        BackendResponse response = new BackendResponse("An error occurred while updating max leaves attribute", "fail", null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
}

