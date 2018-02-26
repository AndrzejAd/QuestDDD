package com.todo.mails.application;

import com.todo.mails.application.commands.SendEmailCommand;

public interface EmailService {
    void sendEmail(SendEmailCommand sendEmailCommand );
}
