package com.designpatterns.singleton;

public class Demo {
	
	public static void main(String[] args) {
		EmailUtility.getInstance().sendMail(); //100X
		EmailUtilityAzure.getInstance().sendMail();
	}


}

/*
 * Step frodo in creating Object 
 * of EmailUtility by making constructor private
 * 
 * 
 * 
 */
