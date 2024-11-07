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
import com.springboot.insurance_app.model.Policy;
import com.springboot.insurance_app.service.PolicyService;

@RestController
public class PolicyController {

	@Autowired
	private PolicyService policyService;
	
	@PostMapping("/policy/add")
	public Policy addPolicy(@RequestBody Policy policy) {
		 //System.out.println(policy);
		return policyService.insert(policy);
	}
	
	@GetMapping("/policy/all")
	public List<Policy> getAllPolicy() {
		List<Policy> list = policyService.getAllPolicy();
		return list;
	}
	
	@DeleteMapping("/policy/delete/{id}")
	public ResponseEntity<?> deletePolicy(@PathVariable int id, ResponseMessageDto dto) {
		//System.out.println(id);
		//validate id
		try {
			policyService.validate(id);
			policyService.delete(id);
		} catch (ResourceNotFoundException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		} 
		dto.setMsg("policy deleted");
		return ResponseEntity.ok(dto);
	}
	
	@PostMapping("/policy/batch/add")
	public List <Policy> batchInsert(@RequestBody List<Policy> list) {
		return policyService.insertInBatch(list);
	}
	
	@PutMapping("/policy/update/{id}")
	public ResponseEntity<?> updatePolicy(@PathVariable int id, @RequestBody Policy newPolicy,ResponseMessageDto dto) {
		try {
			Policy existingPolicyDb = policyService.validate(id);
			if(newPolicy.getTitle() != null)
				existingPolicyDb.setTitle(newPolicy.getTitle());
			if(newPolicy.getDescription() != null)
				existingPolicyDb.setDescription(newPolicy.getDescription());
			if(newPolicy.getPolicyCategory() != null)
				existingPolicyDb.setPolicyCategory(newPolicy.getPolicyCategory());
			if(newPolicy.getPolicyType() != null)
				existingPolicyDb.setPolicyType(newPolicy.getPolicyType());
			
			//re save this existing policy having new updated value 
			existingPolicyDb = policyService.insert(existingPolicyDb);
			return ResponseEntity.ok(existingPolicyDb);
		} catch (ResourceNotFoundException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
	}
}
/*
 * GET : @GetMapping
 * POST: @PostMapping
 * PUT/update: @PutMapping
 * DELETE: @DeleteMapping 
 * 
 */
