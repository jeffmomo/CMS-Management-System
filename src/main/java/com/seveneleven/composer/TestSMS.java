package com.seveneleven.composer;

import java.util.Scanner;

import com.seveneleven.sms.SMSFactory;
import com.seveneleven.sms.SMSSenderInterface;

/**
 * 
 * @author Frensky
 *
 */
public class TestSMS {
	
	public static void main(String[] args){
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.print("To: ");
		String to = sc.nextLine();
		System.out.print("Message: ");
		String body = sc.nextLine();
		
		CSMS c = new CSMS(to, body);
		final SMSSenderInterface SMSSender = SMSFactory.getSMSSender();
		SMSSender.sendSMS(c);
		
		
	}
}
