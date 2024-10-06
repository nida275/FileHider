package service;

import model.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.sound.sampled.Port;
import java.util.Properties;

public class sendOTPService {
    public static void sendOTP(String email, String genOTP) { //email: The recipient’s email address. genOTP: The generated OTP that will be sent to the recipient.
        // Recipient's email ID needs to be mentioned.
        String to = email;

        // Sender's email ID needs to be mentioned
        String from = "nidoosheikh285@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com"; //this line is setting the SMTP server address for Gmail.

        // Get system properties
        Properties properties = System.getProperties(); //File encoding (file.encoding),Operating system (os.name),User's home directory (user.home),Classpath (java.class.path)
        //Now we will be overriding or adding new properties related to the email server (SMTP)

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, "owxuwbwftjhcomqh");
            }
        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("File Enc OTP");

            // Now set the actual message
            message.setText("Your One time Password for File Enc app is " + genOTP);

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }
}
