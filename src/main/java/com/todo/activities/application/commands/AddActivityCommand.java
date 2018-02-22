package com.todo.activities.application.commands;

import com.ddd.common.annotations.Command;
import com.todo.activities.domain.ActivityType;
import com.todo.activities.domain.Progress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Value;

import java.time.Duration;
import java.time.LocalDateTime;

@Command
@Value
public class AddActivityCommand {
    private long activitiesListId;
    private long activityTypeId;
}
