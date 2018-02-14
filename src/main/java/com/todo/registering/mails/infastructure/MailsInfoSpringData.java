package com.todo.registering.mails.infastructure;

import com.todo.registering.mails.application.MailsInfoRepository;
import com.todo.registering.mails.domain.EmailSendingInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailsInfoSpringData extends MailsInfoRepository, JpaRepository<EmailSendingInfo, Long> {
}
