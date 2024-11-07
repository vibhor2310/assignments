package com.sms.service;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.sms.dto.StudentDto;
import com.sms.model.Address;
import com.sms.model.Student;
import com.sms.model.User;
import com.sms.repository.StudentRepository;
import com.sms.utility.DbConnection;

public class StudentService {
	
	private Scanner sc;
	private StudentRepository studentRepository = new StudentRepository(); 
	

	public StudentService(Scanner sc) {
		// TODO Auto-generated constructor stub
		this.sc=sc;
	}

	public Student takeInput() {
		System.out.println("Enter name");
		sc.nextLine();
		String name = sc.nextLine();
		System.out.println("Enter contact");
		String contact = sc.nextLine();
		System.out.println("---Enter User Info------");
		System.out.println("Enter Username:");
		String username = sc.nextLine();
		System.out.println("Enter Password:");
		String password = sc.nextLine();
		System.out.println("---Enter Address Info------");
		System.out.println("Enter City: ");
		String city = sc.nextLine();
		System.out.println("Enter State: ");
		String state = sc.nextLine();
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setRole("STUDENT");
		
		Address address = new Address();
		address.setCity(city);
		address.setState(state);
		
		Student student = new Student();
		student.setName(name);
		student.setContact(contact);
		student.setUser(user);
		student.setAddress(address);
		
		return student;
		
	}

	public void insert(Student student) {
		Connection conn = DbConnection.dbConnect();
		
		// we have set user_id and address_id as auto increment so we have to generate these on our own and add to Db
		
		int userId = (int) (Math.random()*10000000);
		User user = student.getUser();
		user.setId(userId);
		student.setUser(user);
		studentRepository.insertUser(user, conn);
		
		//create address id
		int addressId = (int) (Math.random()*10000000);
	    Address address  = student.getAddress(); 
		address.setId(addressId);
		student.setAddress(address);
		studentRepository.insertAddress(address,conn);
				 
		//create student id
		int studentId = (int) (Math.random()*100000000);
		student.setId(studentId);
		studentRepository.insertStudent(student,conn);
		
		DbConnection.dbClose();
		
	}

	public List<StudentDto> getAllStudentsInfo() {
		List<StudentDto> list = studentRepository.getAllSudentsInfo();
		//asList takes array and converts into list
		List<String> listRural = Arrays.asList(new String [] {"shire","surrey"});
		List<String> listMetro = Arrays.asList(new String[] {"london","chennai"}); 
		
		
		list.stream().forEach(obj->{
			if(listRural.contains(obj.getCity()))
				obj.setCityStatus("RURAL");
			else if(listMetro.contains(obj.getCity()))
				obj.setCityStatus("METRO");
			else
				obj.setCityStatus("unknown");
			
		});
		return list;
		
	}

	public List<StudentDto> filterByCity(List<StudentDto> list, String city) {
		List<StudentDto> filteredList = list.stream().filter(e->e.getCity().equalsIgnoreCase(city)).toList();
		
		return filteredList;
	}

	public List<StudentDto> searchByNameOrUsername(List<StudentDto> list, String search) {
		
		List<StudentDto>listFiltered = list.stream().filter(e->{
			if(e.getUsername().equalsIgnoreCase(search))
				return true;
			else {
				String [] arr = e.getName().split(" ");
				List<String> tempList = Arrays.asList(arr);
				return tempList.contains(search);
				
				
			}
		}).toList();
		
		return listFiltered;
		
	}

}
