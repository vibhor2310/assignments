package com.hibernate.service;

import java.util.List;
import java.util.Scanner;

import com.hibernate.enums.RoleType;
import com.hibernate.model.Address;
import com.hibernate.model.Customer;
import com.hibernate.model.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class CustomerService {
	private EntityManager entityManager;
	private EntityTransaction entityTransaction;

	public CustomerService(EntityManager entityManager, EntityTransaction transaction) {
		this.entityManager = entityManager;
		this.entityTransaction = transaction;
		// TODO Auto-generated constructor stub
	}
	public Customer takeInputAndGenerateId(Scanner sc) {
		Customer customer = new Customer();
		System.out.println("Enter Name:");
		sc.nextLine();
		customer.setName(sc.nextLine());
		System.out.println("Enter age:");
		customer.setAge(sc.nextInt());
		int customerId = (int)(Math.random()*1000000000);
		customer.setId(customerId);
		
		System.out.println("------Address Info--------");
		
		Address address = new Address();
		 System.out.println("Enter city");
		 sc.nextLine();
		 address.setCity(sc.nextLine());
		 
		 System.out.println("Enter state");
		 address.setState(sc.nextLine());
		 
		 int addressId =(int) (Math.random()*10000000);
		 address.setId(addressId);
		 
		 customer.setAddress(address);
		 
		 System.out.println("----Set up credentials for customer----");
			User user = new User();
			System.out.println("Enter username: ");
			user.setUsername(sc.next());
			System.out.println("Enter password");
			user.setPassword(sc.next());
			//set Role
					user.setRole(RoleType.CUSTOMER);
					user.setPasswordReset(false);
					//get ID
					int userId =(int) (Math.random()*1000000000);
					user.setId(userId);
					

					//attach user to Customer
					customer.setUser(user);
		
		return customer;
	}
//	public void saveAddress(Address address) {
//		
//		entityTransaction.begin();
//		entityManager.persist(address);
//		entityTransaction.commit();
//		
//	}
//	public void saveCustomer(Customer customer) {
//		// TODO Auto-generated method stub
//		entityTransaction.begin();
//		entityManager.persist(customer);
//		entityTransaction.commit();
//		
//	}
	public List<Customer> fetchAllCustomer() {
		
		entityTransaction.begin();
		String jpql = "select c from Customer c";
		TypedQuery<Customer>customers = entityManager.createQuery(jpql,Customer.class);
		entityTransaction.commit();
		return customers.getResultList();
	}
	public void saveCustomerUserAddress(Customer customer) {
		// TODO Auto-generated method stub
		entityTransaction.begin();
		entityManager.persist(customer.getUser());
		entityManager.persist(customer.getAddress());
		entityManager.persist(customer);
		entityTransaction.commit();
		
	}
	

}
