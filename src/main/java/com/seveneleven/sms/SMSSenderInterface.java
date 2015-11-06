package com.seveneleven.sms;

import com.seveneleven.composer.CSMS;
/**
 * 
 * @author Frensky
 *
 */
public interface SMSSenderInterface {
	
	/**
     * Interface to send SMS to a target
     * @param objSMS object to be sent
     * @return True if success and false otherwise
     */
	public boolean sendSMS(CSMS objSMS);
	
}
