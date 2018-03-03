package com.quest.mails.domain;

import com.quest.mails.application.commands.SendEmailCommand;

public interface EmailService {
    void sendEmail(SendEmailCommand sendEmailCommand );
}
