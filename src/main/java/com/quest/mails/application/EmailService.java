package com.quest.mails.application;

import com.quest.mails.application.commands.SendEmailCommand;

public interface EmailService {
    void sendEmail(SendEmailCommand sendEmailCommand );
}
