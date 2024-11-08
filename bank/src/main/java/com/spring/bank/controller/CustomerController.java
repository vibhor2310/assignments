package com.spring.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.bank.exception.InvalidCredentialsException;
import com.spring.bank.model.Customer;
import com.spring.bank.model.User;
import com.spring.bank.service.CustomerService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CustomerController {
	
	@Autowired
	private CustomerService customerService; // DI
	
	
	@GetMapping("/")
	public String showLogin() {
		return "login";
		
	}
	
	
	
	@GetMapping("/login-form")
	public String handleLogin(HttpServletRequest req,HttpSession session) {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		try {
			User user = customerService.verifyLogin(username,password);
			if(user.getRole().equalsIgnoreCase("admin")) {
				session.setAttribute("username", username);
//				// fetch all account
				List<Customer> customers = customerService.fetchAllCustomers();
//				
				req.setAttribute("listCustomers", customers);
				return "customer_dashboard"; 
			}
		} catch (InvalidCredentialsException e) {
			req.setAttribute("msg",e.getMessage());
			return "login";
		}
		
		return null;
		
	}
	
	@GetMapping("/customer-dashboard")
	public String goToCustomerDashboard(HttpServletRequest req, HttpSession session) {
		// fetch all courses
		List<Customer> customers = customerService.fetchAllCustomers();
		// List listCourses courseService.fetchAllCourses();
		req.setAttribute("listCustomers", customers);

		return "customer_dashboard";
	}
	
	@GetMapping("/delete-customer")
	public String deleteCustomer(HttpServletRequest req) {
		String cid = req.getParameter("cid");
		customerService.softDelete(cid);
		return "redirect:/customer-dashboard";
	}
	
	@GetMapping("/add-customer")
    public String showAddCustomerForm() {
        return "add_customer";
    }
	
	@PostMapping("/save-customer")
	public String saveCustomer(
	        @RequestParam("name") String name,
	        @RequestParam("contact") String contact,
	        @RequestParam("gender") String gender){
	    
	    Customer newCustomer = new Customer();
	    newCustomer.setName(name);
	    newCustomer.setContact(contact);
	    newCustomer.setGender(gender);

	    
	    customerService.insertCustomer(newCustomer); // Save customer to the database

	    return "redirect:/customer-dashboard"; // Redirect to the home or customer list page
	}

	
	
	
	
	

}
