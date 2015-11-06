package com.seveneleven.sms;

import com.seveneleven.composer.CSMS;
import com.twilio.sdk.*; 
import com.twilio.sdk.resource.factory.*; 
import com.twilio.sdk.resource.instance.Message;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
 
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Frensky
 *
 */
public class SMSSender implements SMSSenderInterface {
	
	public static final String ACCOUNT_SID = "ACb0edf099ab586135149f586124d60f93"; 
	public static final String AUTH_TOKEN = "b7845a1813b46b1ced2415ce97c21c68"; 
	public static final String API_PHONE_NUMBER = "+12564554110";
    
    /**
     * Send an alert through SMS
     * @param objSMS object to be sent
     * @return True if success and false otherwise
     */

	@Override
	public boolean sendSMS(CSMS objSMS){
		TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);

        // Build the parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        //only allows Singapore phone numbers
        params.add(new BasicNameValuePair("From", API_PHONE_NUMBER));
        params.add(new BasicNameValuePair("To", objSMS.getTo()));
        params.add(new BasicNameValuePair("Body", objSMS.getMsg()));

        MessageFactory messageFactory = client.getAccount().getMessageFactory();
        Message message = null;
        try {
            message = messageFactory.create(params);
            System.out.println(message.getSid());
        } catch (TwilioRestException e) {
            e.printStackTrace();
            return false;
        }
        return true;

		
	}
	
	
}
