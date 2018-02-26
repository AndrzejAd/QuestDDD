package com.todo.mails.application.commands;

import com.ddd.common.annotations.Command;
import lombok.Value;

@Command
@Value
public class SendEmailCommand {
    private final String to;
    private final String subject;
    private final String text;
    private final String username;
}
