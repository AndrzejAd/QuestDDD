package com.todo.registering.mails.application;

import com.todo.registering.mails.domain.EmailSendingInfo;

public interface MailsInfoRepository {
    EmailSendingInfo save(EmailSendingInfo emailSendingInfo);
}
