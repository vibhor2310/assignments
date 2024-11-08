package com.hibernate.controller;

import java.util.List;
import java.util.Scanner;

import com.hibernate.model.Customer;
import com.hibernate.service.AuthService;
import com.hibernate.service.CustomerService;
import com.hibernate.utility.FactoryUtility;

import jakarta.persistence.EntityManager;

public class CustomerController {
	public static void main(String[] args) {
		EntityManager entityManager = FactoryUtility.getInstance().loadPersistence();
		Scanner sc = new Scanner(System.in);
		CustomerService customerService = new CustomerService(entityManager,entityManager.getTransaction());
		
		AuthService authService = new AuthService(entityManager);
		System.out.println("----------Ecom LOGIN-------------");
		System.out.println("Enter Username:");
		String username = sc.next();
		System.out.println("Enter password:");
		String password = sc.next();
		
		boolean isAdmin = authService.checkIfAdmin(username,password);
		
		if(isAdmin == true) {
			while(true) {
				System.out.println("Welcome "+username);
				System.out.println("-----Admin Menu-----");
				System.out.println("1. Add customer");
				int input = sc.nextInt();
				
				if(input == 0) {
					System.out.println("Exiting , thank you!!");
					break; 
				}
				
				switch(input){
				case 1:
					//take input
					Customer customer = customerService.takeInputAndGenerateId(sc);
					// to check if its unique or not
					boolean isUnique = authService.checkIfUsernameUnique(customer.getUser().getUsername());
					if(isUnique == true) {
						//save customer and user in DB
						customerService.saveCustomerUserAddress(customer);
						System.out.println("Customer added in DB");
					}
					else {
						System.out.println("Username already in use...");
					}
//					//save address 
//					customerService.saveAddress(customer.getAddress());
//					//save customer
//					customerService.saveCustomer(customer);
					break;
				default:
					break;
					
				}
				
			}
		}
		else {
		while(true) {
			System.out.println("------------Product MENU--------------");
			System.out.println("1. Enter Customer with Address in DB");
			System.out.println("2. Fetch All Customer with Address");
			System.out.println("0. Exit");
			int input =sc.nextInt();
			if(input == 0 ) {
				System.out.println("Exiting.. thank you");
				break; 
			}
			
			switch(input) {
			case 1:
//				//take input
//				Customer customer = customerService.takeInputAndGenerateId(sc);
//				//save address 
//				customerService.saveAddress(customer.getAddress());
//				//save customer
//				customerService.saveCustomer(customer);
//				System.out.println("customer with address added to database...");
//				break;
			case 2:
				List<Customer> list = customerService.fetchAllCustomer();
				list.stream().forEach(e->System.out.println(e));
				break;
			default:
				System.out.println("Invalid Input, Try Again!!");
				break;
			}
		  }
		}
		
		sc.close();
	}

}
