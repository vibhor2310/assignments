package com.spring.bank.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spring.bank.exception.InvalidCredentialsException;
import com.spring.bank.model.Customer;
import com.spring.bank.model.User;

@Repository
public class CustomerRepository {
	
	@Autowired
	private JdbcTemplate jdbc;

	public User verifyLogin(String username, String password) throws InvalidCredentialsException {
		String sql = "select * from User where username=? and password=?";
		PreparedStatementCreator psc = new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, username);
				pstmt.setString(2, password);
				return pstmt;
			}
		};

		List<User> list = jdbc.query(psc, new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getInt("user_id"));
				user.setUsername(username);
				user.setPassword(password);
				user.setRole(rs.getString("role"));
				return user;
			}

		});
		if (list.isEmpty()) {
			throw new InvalidCredentialsException("Invalid Credentials");
		} else
			return list.get(0);
	}
	
	public List<Customer> fetchAllCustomers() {
		// prepare the statement
		String sql = "select customer_id, name, contact, gender from customer c where  c.is_active=?";

		PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setBoolean(1, true);
				return pstmt;
			}

		};

		RowMapper<Customer> rowMapper = new RowMapper<Customer>() {

			@Override
			public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {

				Customer customer = new Customer();

				int customerId = rs.getInt("customer_id");
				String name = rs.getString("name");
				String contact = rs.getString("contact");
				String gender = rs.getString("gender");

				customer.setId(customerId);
				customer.setName(name);
				customer.setContact(contact);
				customer.setGender(gender);

				return customer;
			}
		};

		List<Customer> list = jdbc.query(psc, rowMapper);

		return list;

	}

	public void softDelete(int cid) {
		String sql = "update customer set is_active=false where customer_id=?";
		PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, cid);
				return pstmt;
			}

		};

		jdbc.update(psc);

	}

	public void insertCustomer(Customer customer) {
		
	        String sql = "INSERT INTO customer (name, contact, gender) VALUES (?, ?, ?)";
	        PreparedStatementCreator psc = new PreparedStatementCreator()
	        {
	        	@Override
	        	public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
	        		 PreparedStatement pstmt = con.prepareStatement(sql);
	                 pstmt.setString(1, customer.getName());
	                 pstmt.setString(2, customer.getContact());
	                 pstmt.setString(3, customer.getGender());
	               
	                 return pstmt;
	        
			}
	        	
	        	};


	            jdbc.update(psc);
	       
		
	}

//	public void save(Customer newCustomer) {
//		 String sql = "INSERT INTO customer (name, contact, gender) VALUES (?, ?, ?)";
//	        PreparedStatementCreator psc = new PreparedStatementCreator()
//	        {
//	        	@Override
//	        	public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//	        		 PreparedStatement pstmt = con.prepareStatement(sql);
//	                 pstmt.setString(1, newCustomer.getName());
//	                 pstmt.setString(2, newCustomer.getContact());
//	                 pstmt.setString(3, newCustomer.getGender());
//	                 return pstmt;
//	        
//			}
//	        	
//	        	};
//
//
//	            jdbc.update(psc);
//
//		
//	}

}
