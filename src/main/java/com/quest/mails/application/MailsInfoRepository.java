package com.quest.mails.application;

import com.quest.mails.domain.EmailSendingInfo;

public interface MailsInfoRepository {
    EmailSendingInfo save(EmailSendingInfo emailSendingInfo);
}
