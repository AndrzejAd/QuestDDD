package com.todo.activities.application.commands;

import com.ddd.common.annotations.Command;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Value;

@Command
@Value
public class CreateNewActivitiesListCommand {
    private long userId;
}
