package com.sms.service;

import java.util.List;
import java.util.Scanner;

import com.sms.exception.InvalidException;
import com.sms.exception.ResourceNotFoundException;
import com.sms.model.Instructor;
import com.sms.repository.InstructorRepository;

public class InstructorService {
	
	private Scanner sc;
	private InstructorRepository instructorRepository = new InstructorRepository();

	public InstructorService(Scanner sc) {
		// TODO Auto-generated constructor stub
		this.sc = sc;
	}
	
	public InstructorService() {
		 
	}

	public Instructor takeInput() {
		 System.out.println("Enter name");
		 sc.nextLine();
		 String name = sc.nextLine();
		 System.out.println("Enter contact");
		 String contact = sc.next();
		 System.out.println("Enter salary");
		 double salary = sc.nextDouble();
		 Instructor instructor = new Instructor();
		 instructor.setName(name);
		 instructor.setContact(contact);
		 instructor.setSalary(salary);
		 return instructor;
		 
	}
	
	public void insert(Instructor instructor) {
		
		if(instructor.getSalary()>200000) {
			instructor.setJobTitle("Senior".toUpperCase());
		}
		else if(instructor.getSalary()>125000) {
			instructor.setJobTitle("Associate".toUpperCase());
		}
		else
			instructor.setJobTitle("Junior".toUpperCase());
		
		instructorRepository.insert(instructor);
		
		
		
	}

	public void validate(Instructor instructor) throws InvalidException {
		if(instructor.getName() == null) 
			throw new InvalidException("Name cannot be blank");
	if(instructor.getContact() == null) 
		throw new InvalidException("Contact cannot be blank");
	if(instructor.getSalary() == 0) 
		throw new InvalidException("Salary cannot be 0");
	}

	public List<Instructor> getAllInstructors() {
		// TODO Auto-generated method stub
		return instructorRepository.getAllInstructors();
	}

	public void validateId(int id) throws ResourceNotFoundException {
		
		if(!instructorRepository.getInstructorById(id))
			throw new ResourceNotFoundException("Invalid id Given");
		
		}

	public void deleteById(int id) {
		// TODO Auto-generated method stub
		instructorRepository.deleteById(id);
		
	}

	public Instructor validateIdAndFetchRecord(int id) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return instructorRepository.validateIdAndFetchRecord(id);
		
	}

	public void update(Instructor obj) {
		instructorRepository.update(obj);
		// TODO Auto-generated method stub
		
	}

	

}
