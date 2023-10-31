package Common;

import javax.mail.*;
import java.util.Properties;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

    public static void main(String[] args) {
        String email = "taidmhe150076@fpt.edu.vn";
        int otp = 1234;
        Send(email, otp);
    }

    public static void Send(String email, int otp) {

        final String fromEmail = "taidmhe01@outlook.com";
        final String password = "@Tai01062001";

// Config SMTP
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.office365.com"); // smtp.office365.com
        props.put("mail.smtp.port", "587");

// Create session
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

// send email
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject("Verify");
            message.setText("Otp Code : " + otp);

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
