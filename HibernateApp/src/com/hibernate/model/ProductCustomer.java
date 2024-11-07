package com.hibernate.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_customer")
public class ProductCustomer {
	
	@Id
	private int id;
	
	int qty;
	
	private LocalDate purchaseDate;
	
	@ManyToOne
	private Product product;
	
	@ManyToOne
	private Customer customer;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "ProductCustomer [id=" + id + ", product=" + product + ", customer=" + customer + "]";
	}
	
	
	
	
	
	

}
