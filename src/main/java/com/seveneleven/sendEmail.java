package com.seveneleven;


import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class sendEmail
{
	public sendEmail(){}
	private static final String SENDERS_EMAIL = "pmreporttesting@gmail.com";
	 private static final String SENDERS_PWD = "zhaojingying";
	 private static final String RECIPIENTS_EMAIL = "zjyclaire1023@gmail.com";
   public  static void sendEmailwithAttachment()
   {
	  
	   Properties mailProps = new Properties();
		 
		 // Set properties required to connect to Gmail's SMTP server
		 mailProps.put("mail.smtp.host", "smtp.gmail.com");
		 mailProps.put("mail.smtp.port", "587"); 
		 mailProps.put("mail.smtp.auth", "true");
		 mailProps.put("mail.smtp.starttls.enable", "true"); 
		 
		 // Create a username-password authenticator to authenticate SMTP session
		 Authenticator authenticator = new Authenticator() {
		         //override the getPasswordAuthentication method
		         protected PasswordAuthentication getPasswordAuthentication() {
		             return new PasswordAuthentication(SENDERS_EMAIL, SENDERS_PWD);
		         }
		     };
		 
		     // Create the mail session
		 Session session = Session.getDefaultInstance(mailProps, authenticator);
		 try{
		 // Create a default MimeMessage object.
		 final MimeMessage message = new MimeMessage(session);
		 
		 // Set the sender's email address
		 message.setFrom(new InternetAddress(SENDERS_EMAIL));
		 
		 // Set recipient's email address
		 message.addRecipient(Message.RecipientType.TO, new InternetAddress(RECIPIENTS_EMAIL));
		 
		 // Set the subject of the email
		 message.setSubject("Hello!");
		 
		 // Now set the actual message body of the email
		 message.setText("This email was sent using JavaMail API through Gmail! Isn't it awesome?");
		
		 // Set Subject: header field
       message.setSubject("This is the report for PM");

       // Create the message part 
       BodyPart messageBodyPart = new MimeBodyPart();

       // Fill the message
       messageBodyPart.setText("Please check the attahment for the report");
		 // Create a multipar message
       Multipart multipart = new MimeMultipart();

       // Set text message part
       multipart.addBodyPart(messageBodyPart);

       // Part two is attachment
       messageBodyPart = new MimeBodyPart();
       String filename = "report.txt";
       DataSource source = new FileDataSource(filename);
       messageBodyPart.setDataHandler(new DataHandler(source));
       messageBodyPart.setFileName(filename);
       multipart.addBodyPart(messageBodyPart);

       // Send the complete message parts
       message.setContent(multipart );
		 System.out.println("Sending email...");
		 
		 // Send message
		 Transport.send(message);
		 
		 System.out.println("Email sent!");
		 
		 }catch(Exception e){
		 System.err.println("Problem sending email. Exception : " + e.getMessage());
		 }
		 }
}
