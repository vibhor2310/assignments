package com.sms.controller;

import java.util.List;

import com.sms.model.Instructor;
import com.sms.service.InstructorService;

public class LambdaDemo3 {
	public static void main(String[] args) {
		List<Instructor> list = new InstructorService().getAllInstructors();
		list.stream().forEach(e->System.out.println(e));
		
		//map : reduces the list to a particular field
				/*Display all the names of Instructor 
				 * List<Instructor> : List<String> */
		
		List<String>listNames = list.stream().map((i)->i.getName()).toList();
		System.out.println(listNames);
		
		List<Double> listSalary = list.stream().map(Instructor :: getSalary).toList();
		System.out.println(listSalary);
		
		//filter: display instructors having salary > 80000 
		
		list = list.stream().filter(e->e.getSalary()>80000).toList();
		System.out.println(list);
		
		
		
		
	}
	
	

}


/*
 * Consumer : forEach : needs any lambda 
 * Function : method/field name 
 * Predicate : true/false 
 */
