/*
 * Xuan Son
 * 
 * Mar 2, 2022
 *
 */
package utility;

import java.util.Properties;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Xuan Son
 */
public class SendMail {
//generate vrification code

    public String getRandom() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }

    // this is responsible to send the message with attachment
    public boolean sendAttach(String message, String subject, String to,
            String from) {
        boolean test = false;
        // Variable for gmail
        String host = "smtp.gmail.com";
        // get the system properties
        Properties properties = System.getProperties();
        System.out.println("PROPERTIES " + properties);
        // setting important information to properties object
        // host set
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        // Step 1: to get the session object..
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("hauzter57@gmail.com",
                        "ihope123");
            }
        });
        session.setDebug(true);
        // Step 2 : compose the message [text,multi media]
        MimeMessage m = new MimeMessage(session);
        try {
            // from email
            m.setFrom(from);
            // adding recipient to message
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            // adding subject to message
            m.setSubject(subject);
            // attachement..
            // file path
//			String path = "C:\\Users\\Admin\\Downloads\\unnamed.png";
            MimeMultipart mimeMultipart = new MimeMultipart();
            // text
            // file
            MimeBodyPart textMime = new MimeBodyPart();
//			MimeBodyPart fileMime = new MimeBodyPart();
            try {
                textMime.setContent(message, "text/html");
//				File file = new File(path);
//				fileMime.attachFile(file);
                mimeMultipart.addBodyPart(textMime);
//				mimeMultipart.addBodyPart(fileMime);
            } catch (Exception e) {
                e.printStackTrace();
            }
            m.setContent(mimeMultipart);
            // send
            // Step 3 : send the message using Transport class
            Transport.send(m);
            test = true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Email not correct!");
        }
        System.out.println("Sent success...................");

        return test;
    }
}
