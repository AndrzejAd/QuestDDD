package com.todo.registering.mails.application;

import com.ddd.common.annotations.ApplicationService;
import com.todo.registering.mails.application.commands.SendEmailCommand;
import com.todo.registering.mails.domain.Email;
import com.todo.registering.mails.domain.EmailSendingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@ApplicationService
public class MailSenderService implements EmailService{
    private final MailSenderImpl mailSender;
    private final MailsInfoRepository mailsInfoRepository;

    @Autowired
    public MailSenderService(MailSenderImpl mailSender, MailsInfoRepository mailsInfoRepository) {
        this.mailSender = mailSender;
        this.mailsInfoRepository = mailsInfoRepository;
    }

    @Override
    @Transactional( propagation  = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public void sendEmail(SendEmailCommand sendEmailCommand){
        mailSender.sendEmail( new Email(
                    sendEmailCommand.getTo(),
                    sendEmailCommand.getSubject(),
                    sendEmailCommand.getText(),
                    sendEmailCommand.getUsername()
                )
        );
        mailsInfoRepository.save(new EmailSendingInfo(
                sendEmailCommand.getTo(),
                sendEmailCommand.getSubject()
        ));
    }

}
