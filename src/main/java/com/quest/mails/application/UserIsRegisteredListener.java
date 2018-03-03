package com.quest.mails.application;

import com.ddd.common.annotations.EventListener;
import com.ddd.common.events.UserIsRegistered;
import com.quest.mails.application.commands.SendEmailCommand;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

@EventListener
@RequiredArgsConstructor
public class UserIsRegisteredListener implements ApplicationListener<UserIsRegistered> {
    private final Logger logger = LogManager.getLogger(UserIsRegisteredListener.class);
    private final EmailService emailService;

    @Override
    public void onApplicationEvent(UserIsRegistered userIsRegistered) {
        logger.info("Account with id: " + userIsRegistered.getUserId() + " is registered, sending mail.");
        emailService.sendEmail(
                new SendEmailCommand(
                        userIsRegistered.getEmail(),
                        "Register email.",
                        "Hello to the ToDo app!",
                        userIsRegistered.getUsername()));
    }
}
