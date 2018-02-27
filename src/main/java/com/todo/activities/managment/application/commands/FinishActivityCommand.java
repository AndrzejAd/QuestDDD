package com.todo.activities.managment.application.commands;

import com.ddd.common.annotations.Command;
import lombok.Value;

@Command
@Value
public class FinishActivityCommand {
    private final long activityId;
}
