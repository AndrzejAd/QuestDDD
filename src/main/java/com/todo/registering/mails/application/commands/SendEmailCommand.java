package com.todo.registering.mails.application.commands;

import com.todo.common.annotations.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Command
@AllArgsConstructor
@Getter
public class SendEmailCommand {
    private final String to;
    private final String subject;
    private final String text;
}
