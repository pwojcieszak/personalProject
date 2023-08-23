package com.pwojcieszak.notificationService.service;

import com.pwojcieszak.notificationService.event.SkillCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;


@Service
@RequiredArgsConstructor
public class EmailService {
    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private String port;

    @Value("${spring.mail.addresses.from}")
    private String emailFromAddress;

    @Value("${spring.mail.replyTo}")
    private String emailReplyToAddress;

    public void sendEmailToAdmin(SkillCreatedEvent event) {
        Properties properties = getProperties();
        Session session = Session.getInstance(properties);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(emailFromAddress);
            message.setRecipients(Message.RecipientType.TO, emailReplyToAddress);
            message.setSubject("Testing Javax Mail and MailHog");
            message.setSentDate(new Date());

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent("New action \n " + event.toString(), "text/plain");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);

            Transport.send(message);
        }catch (Exception exception) {
            System.out.println("Sending Email failed, error : " + exception.getMessage());
        }
    }

    private Properties getProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);

        return properties;
    }
}
