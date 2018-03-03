package com.quest.mails.application.service;

import com.ddd.common.annotations.ApplicationService;
import com.quest.mails.application.commands.SendEmailCommand;
import com.quest.mails.domain.EmailService;
import com.quest.mails.domain.MailSenderImpl;
import com.quest.mails.domain.Email;
import com.quest.mails.domain.EmailSendingInfo;
import com.quest.mails.domain.MailsInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@ApplicationService
@RequiredArgsConstructor
public class MailSenderService implements EmailService {
    private final MailSenderImpl mailSender;
    private final MailsInfoRepository mailsInfoRepository;

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
