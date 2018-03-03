package com.quest.mails.application.listeners;

import com.ddd.common.annotations.EventListener;
import com.ddd.common.events.ActivityIsFinished;
import com.quest.mails.application.commands.SendEmailCommand;
import com.quest.mails.application.internal.EmailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

@EventListener
public class ActivityIsFinishedListener implements ApplicationListener<ActivityIsFinished> {
    private final Logger logger = LogManager.getLogger(ActivityIsFinishedListener.class);
    private final EmailService emailService;

    @Autowired
    public ActivityIsFinishedListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void onApplicationEvent(ActivityIsFinished activityIsFinished) {
        logger.info("Activity with id: " + activityIsFinished.getId() + " is finished, sending mail.");
        emailService.sendEmail(
                new SendEmailCommand(
                        activityIsFinished.getEmail(),
                        "You finished some activity!",
                        " nice, you got "
                                + activityIsFinished.getXpGained() + " xp!",
                        activityIsFinished.getUsername()));
    }
}
