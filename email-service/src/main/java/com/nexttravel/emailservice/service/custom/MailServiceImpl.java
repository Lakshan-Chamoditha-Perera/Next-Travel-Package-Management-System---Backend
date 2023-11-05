package com.nexttravel.emailservice.service.custom;

import com.nexttravel.emailservice.model.EMAILS;
import com.nexttravel.emailservice.model.MailStructure;
import com.nexttravel.emailservice.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String FROM;

    public void welcome(String mail, MailStructure mailStructure) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(mail);
        simpleMailMessage.setFrom(FROM);
        simpleMailMessage.setSubject("Welcome to Next Travel Pvt Ltd.!");
        simpleMailMessage.setText(EMAILS.WELCOME.getSubject());
        javaMailSender.send(simpleMailMessage);

    }
}
