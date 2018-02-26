package com.todo.mails.application;

import com.todo.mails.domain.EmailSendingInfo;

public interface MailsInfoRepository {
    EmailSendingInfo save(EmailSendingInfo emailSendingInfo);
}
