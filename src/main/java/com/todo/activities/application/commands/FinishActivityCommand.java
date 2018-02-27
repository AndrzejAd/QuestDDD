package com.todo.activities.application.commands;

import com.ddd.common.annotations.Command;
import lombok.Value;

@Command
@Value
public class FinishActivityCommand {
    private final long activityId;
}
