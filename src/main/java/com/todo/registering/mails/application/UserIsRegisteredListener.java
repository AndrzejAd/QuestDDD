package com.todo.registering.mails.application;

import com.todo.common.annotations.EventListener;
import com.todo.common.events.UserIsRegistered;
import com.todo.registering.mails.application.commands.SendEmailCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

@EventListener
public class UserIsRegisteredListener implements ApplicationListener<UserIsRegistered> {
    private final Logger logger = LogManager.getLogger(UserIsRegisteredListener.class);
    private final MailSenderService mailSenderService;

    @Autowired
    public UserIsRegisteredListener(MailSenderService mailSenderService) {
        this.mailSenderService = mailSenderService;
    }

    @Override
    public void onApplicationEvent(UserIsRegistered userIsRegistered) {
        logger.info("User with id: " + userIsRegistered.getUserId() + " is registered, sending mail.");
        mailSenderService.sendEmail(
                new SendEmailCommand(
                        userIsRegistered.getEmail(),
                        "Register email.",
                        "Hello to the ToDo app!",
                        userIsRegistered.getUsername()));
    }
}