package com.todo.registering.mails.application.commands;

import com.ddd.common.annotations.Command;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Command
@Data
public class SendEmailCommand {
    private final String to;
    private final String subject;
    private final String text;
    private final String username;
}
