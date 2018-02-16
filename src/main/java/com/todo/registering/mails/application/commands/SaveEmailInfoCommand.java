package com.todo.registering.mails.application.commands;

import com.ddd.common.annotations.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Command
@AllArgsConstructor
@Getter
public class SaveEmailInfoCommand {
    private final String address;
    private final String subject;
}
