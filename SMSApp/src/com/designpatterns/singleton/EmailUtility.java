package com.designpatterns.singleton;


//<-- Singleton Design Pattern
public class EmailUtility {
	String fromEmail;
	String toEmail;
	String subject;
	String content;
	
	static EmailUtility emailutility;
	
	static {
		emailutility = new EmailUtility(); //100X
	}
	
     private EmailUtility(){
		
	}
     
	EmailUtility(String str){ }
	
	
	public static EmailUtility getInstance() {
		return emailutility;
		
	}
	
	public void sendMail() {
		
		System.out.println("connection details of server 1");
		System.out.println("Mail send with following info");
		System.out.println("From Email: " + fromEmail);
		System.out.println("To Email: " + toEmail);
		System.out.println("Subject: " + subject);
		System.out.println("Email Body: " + content);
	}

}