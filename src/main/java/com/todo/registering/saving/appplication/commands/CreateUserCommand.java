package com.todo.registering.saving.appplication.commands;

import com.todo.common.annotations.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Command
@AllArgsConstructor
@Getter
public class CreateUserCommand {
    private final String emailAddress;
    private final String country;
    private final String city;
    private final String username;
    private final String password;
    private final long epochBirthDate;
}
