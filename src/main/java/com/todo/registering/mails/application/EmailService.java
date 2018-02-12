package com.todo.registering.mails.application;

public interface EmailService {
    void sendEmail(String to, String subject, String text );
}
