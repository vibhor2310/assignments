package com.springboot.insurance_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.insurance_app.dto.ResponseMessageDto;
import com.springboot.insurance_app.exception.ResourceNotFoundException;
import com.springboot.insurance_app.model.PolicyHolder;
import com.springboot.insurance_app.service.PolicyHolderService;

@RestController
public class PolicyHolderController {
	
	@Autowired
	private PolicyHolderService policyHolderService;
	
	@PostMapping("/policyHolder/add")
	public PolicyHolder addPolicyHolder(@RequestBody PolicyHolder policyHolder) {
		return policyHolderService.insert(policyHolder);
		
	}
	
	@GetMapping("/policyHolder/all")
	public List<PolicyHolder> getAllPolicyHolder(){
		List<PolicyHolder> list = policyHolderService.getAllPolicyHolder();
		return list;
		
	}
	
	@DeleteMapping("/policyHolder/delete/{id}")
	public ResponseEntity<?> deletePolicyHolder(@PathVariable int id,ResponseMessageDto dto) {
		
		try {
			policyHolderService.validate(id);
			policyHolderService.delete(id);
			
		} catch (ResourceNotFoundException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
			
		}
		
		 dto.setMsg("Deleted aucessfully...");
		 return ResponseEntity.ok(dto);
		
		
	}
	
	@PutMapping("/policyHolder/update/{id}")
	public ResponseEntity<?>updatePolicyHolder(@PathVariable int id,@RequestBody PolicyHolder newPolicyHolder,ResponseMessageDto dto) {
		try {
			PolicyHolder existingPolicyHolderDb = policyHolderService.validate(id);
			if(newPolicyHolder.getName() !=null)
				existingPolicyHolderDb.setName(newPolicyHolder.getName());
			if(newPolicyHolder.getContact() !=null)
				existingPolicyHolderDb.setContact(newPolicyHolder.getContact());
			if(newPolicyHolder.getPanNumber() !=null)
				existingPolicyHolderDb.setPanNumber(newPolicyHolder.getPanNumber());
			if(newPolicyHolder.getAge() !=0)
				existingPolicyHolderDb.setAge(newPolicyHolder.getAge());
			//re save this existing policy having new updated value 
			existingPolicyHolderDb = policyHolderService.insert(existingPolicyHolderDb);
			return ResponseEntity.ok(existingPolicyHolderDb);
				
		} catch (ResourceNotFoundException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
		
		
		
	}
	
	
	
	
	

}
