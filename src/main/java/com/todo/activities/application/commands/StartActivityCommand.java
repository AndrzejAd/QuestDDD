package com.todo.activities.application.commands;

import com.ddd.common.annotations.Command;
import lombok.Getter;
import lombok.Value;

@Command
@Value
public class StartActivityCommand {
    private final long activityId;
}
