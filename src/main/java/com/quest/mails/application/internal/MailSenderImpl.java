package com.quest.mails.application.internal;

import com.ddd.common.annotations.DomainService;
import com.quest.mails.domain.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@DomainService
public class MailSenderImpl{
    private final JavaMailSender javaMailSender;

    @Autowired
    public MailSenderImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(Email email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email.getAddress());
        message.setSubject(email.getSubject());
        message.setText(email.getText() + " " + email.getSendDate());
        javaMailSender.send(message);
    }

}
