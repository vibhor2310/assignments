package com.sms.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	
	static Connection con;
	
	public static Connection dbConnect() {
		
		// Load the driver
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
//			System.out.println("Driver class loaded");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// establish the connection
	     
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sms","root","vibhor23");
//			System.out.println("Connection est...");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
		
	}
	
	public static void dbClose() {
		// close the connection
		
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		DbConnection.dbConnect();
		DbConnection.dbClose();
		
	}
	

}
