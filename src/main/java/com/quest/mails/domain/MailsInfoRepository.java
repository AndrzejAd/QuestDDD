package com.quest.mails.domain;

import com.quest.mails.domain.EmailSendingInfo;

public interface MailsInfoRepository {
    EmailSendingInfo save(EmailSendingInfo emailSendingInfo);
}
