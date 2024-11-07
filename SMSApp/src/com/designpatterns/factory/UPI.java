package com.designpatterns.factory;

public class UPI extends Payment{

	@Override
	public void processPayment(String acctNo, double amount, TransactionType type) {
		// TODO Auto-generated method stub
		System.out.println("UPI does this ....");
		
	}

}
