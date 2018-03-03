package com.quest.mails.infastructure;

import com.quest.mails.application.MailsInfoRepository;
import com.quest.mails.domain.EmailSendingInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailsInfoSpringData extends MailsInfoRepository, JpaRepository<EmailSendingInfo, Long> {
}
