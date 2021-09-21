package com.example.demo.service;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.example.demo.exception.SpringRedditException;
import com.example.demo.model.NotificationEmail;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class MailService {
	
	private final JavaMailSender mailSender;
	private final MailContentBuilder mailContentBuilder;
	
	public void sendMail(NotificationEmail notificationEmail) {
		
		MimeMessagePreparator messagePreparator = mimeMessage -> { 
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setFrom("dontreply@email.com");
			messageHelper.setTo(notificationEmail.getRecipient());
			messageHelper.setSubject(notificationEmail.getSubject());
			messageHelper.setText(mailContentBuilder.build(notificationEmail.getBody()));
			
		};
		try {
			mailSender.send(messagePreparator);
			log.info("Activation Email Sent");
		}catch(MailException e) {
			throw new SpringRedditException("There was a problem sending an activation email to " + notificationEmail.getRecipient());
		}
	}
	
}
