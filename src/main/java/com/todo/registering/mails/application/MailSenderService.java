package com.todo.registering.mails.application;

import com.todo.common.annotations.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;

@ApplicationService
public class MailSenderService {
    private final MailSenderImpl mailSender;
    private final MailsInfoRepository mailsInfoRepository;

    @Autowired
    public MailSenderService(MailSenderImpl mailSender, MailsInfoRepository mailsInfoRepository) {
        this.mailSender = mailSender;
        this.mailsInfoRepository = mailsInfoRepository;
    }

    public void sendEmail(SendEmailCommand sendEmailCommand){
        mailSender.sendEmail(
                sendEmailCommand.getTo(),
                sendEmailCommand.getSubject(),
                sendEmailCommand.getText()
        );

    }

}
