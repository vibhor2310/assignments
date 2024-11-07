package com.spring.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bank.exception.InvalidCredentialsException;
import com.spring.bank.model.Customer;
import com.spring.bank.model.User;
import com.spring.bank.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	public User verifyLogin(String username, String password) throws InvalidCredentialsException {
		
		return customerRepository.verifyLogin(username,password);
			
	}

   public List<Customer> fetchAllCustomers() {
		
		return customerRepository.fetchAllCustomers();
	}

	public void softDelete(String cid) {
		
		customerRepository.softDelete(Integer.parseInt(cid));
		
	}

	public void insertCustomer(Customer customer) {
	 customerRepository.insertCustomer(customer);
		
	}

}
