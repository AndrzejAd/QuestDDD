package com.todo.activities.application.commands;

import com.ddd.common.annotations.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Command
@AllArgsConstructor
@Getter
public class CreateNewActivitiesListCommand {
    private long userId;
}
