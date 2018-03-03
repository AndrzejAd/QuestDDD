package com.quest.mails.application.internal;

import com.quest.mails.application.commands.SendEmailCommand;

public interface EmailService {
    void sendEmail(SendEmailCommand sendEmailCommand );
}
