package com.springboot.insurance_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.insurance_app.model.Claim;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Integer>{

}
