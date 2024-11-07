package com.designpatterns.factory;

import java.util.Scanner;

public class App {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Choose payment Method");
		System.out.println("1.UPI");
		System.out.println("2.NEFT");
		System.out.println("3.RTGS");
		int input = sc.nextInt();
		switch(input) {
		case 1:
			Payment paymentUpi  = PaymentFactory.getInstance(PaymentType.UPI);
			paymentUpi.processPayment("AC4445655", 10000, TransactionType.DEBIT);
			break;
		case 2:
			Payment paymentNeft  = PaymentFactory.getInstance(PaymentType.NEFT);
			paymentNeft.processPayment("AC4445655", 10000, TransactionType.DEBIT);	
			break;
		case 3:
			Payment paymentRtgs  = PaymentFactory.getInstance(PaymentType.RTGS);
			paymentRtgs.processPayment("AC4445655", 10000, TransactionType.DEBIT);
			break;
		}
		
		
		sc.close();
	}
}
