package com.springboot.insurance_app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.insurance_app.exception.ResourceNotFoundException;
import com.springboot.insurance_app.model.Executive;
import com.springboot.insurance_app.repository.ExecutiveRepository;

@Service
public class ExecutiveService {
	
	@Autowired
	private ExecutiveRepository executiveRepository;
	
	public Executive insert(Executive executive) {
		 
		return executiveRepository.save(executive);
	}

	public Executive validate(int executiveId) throws ResourceNotFoundException {
		Optional<Executive>optional =executiveRepository.findById(executiveId);
		if(optional.isEmpty()) {
			throw new ResourceNotFoundException("THE GIVEN ID IS NOT valid");
		}
		
		Executive executive = optional.get();
		return executive;
	}


}
