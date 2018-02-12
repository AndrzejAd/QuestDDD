package com.todo.mails.application;

public interface EmailService {
    void sendEmail(String to, String subject, String text );
}
