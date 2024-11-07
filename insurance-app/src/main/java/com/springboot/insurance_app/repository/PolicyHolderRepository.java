package com.springboot.insurance_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.insurance_app.model.PolicyHolder;

@Repository
public interface PolicyHolderRepository extends JpaRepository<PolicyHolder, Integer>{
	

}
