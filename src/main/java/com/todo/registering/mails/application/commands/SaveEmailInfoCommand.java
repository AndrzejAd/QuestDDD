package com.todo.registering.mails.application.commands;

import com.ddd.common.annotations.Command;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Value;

@Command
@Value
public class SaveEmailInfoCommand {
    private final String address;
    private final String subject;
}
