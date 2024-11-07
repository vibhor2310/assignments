package com.designpatterns.singleton;

public class App {
	public static void main(String[] args) {
		EmailUtility.getInstance().sendMail();
	}

}

/*
 * App_STACK
 * EmailUtility emailUtility: 100X
 * 
 * Demo_STACK
 * EmailUtility emailUtility: 200X
 * 
 * HEAP
 * ----
 * 100X:new EmailUtility() -- *****
 * 200X:new EmailUtility()
 * 300X:
 * 400X:
 * 
 * static
 * ------- //reset password: 
 */
