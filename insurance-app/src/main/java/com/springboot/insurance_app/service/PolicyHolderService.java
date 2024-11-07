package com.springboot.insurance_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.insurance_app.exception.ResourceNotFoundException;
import com.springboot.insurance_app.model.PolicyHolder;
import com.springboot.insurance_app.repository.PolicyHolderRepository;

@Service
public class PolicyHolderService {
	
	@Autowired
	private PolicyHolderRepository policyHolderRepository;

	public PolicyHolder insert(PolicyHolder policyHolder) {
		return policyHolderRepository.save(policyHolder);
		
	}

	public List<PolicyHolder> getAllPolicyHolder() {
		return policyHolderRepository.findAll();
	}

	public void delete(int id) {
		 policyHolderRepository.deleteById(id);
		
	}

	public PolicyHolder validate(int id) throws ResourceNotFoundException {
		Optional<PolicyHolder>optional =policyHolderRepository.findById(id);
		if(optional.isEmpty()) {
			throw new ResourceNotFoundException("THE GIVEN ID IS NOT valid");
		}
		
		PolicyHolder policyHolder = optional.get();
		return policyHolder;
		
		
	}

}
