package com.todo.mails.infastructure;

import com.todo.mails.application.MailsInfoRepository;
import com.todo.mails.domain.EmailSendingInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailsInfoSpringData extends MailsInfoRepository, JpaRepository<EmailSendingInfo, Long> {
}
