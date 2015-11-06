package com.seveneleven.sms;

/**
 * 
 * @author Frensky
 *
 */
public class SMSFactory {
	
	private static final SMSSenderInterface objSMS = new SMSSender();
	
    /**
     * Static factory to create and returns implementation of SMS sender to the caller
     * @return The concrete SMS sender implementation object
     */
    public static SMSSenderInterface getSMSSender() {
        return objSMS;
    }
}
