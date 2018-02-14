package com.todo.registering.mails.application;

import com.todo.registering.mails.application.commands.SendEmailCommand;

public interface EmailService {
    void sendEmail(SendEmailCommand sendEmailCommand );
}
