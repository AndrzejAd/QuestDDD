package com.todo.activities.application.commands;

import com.ddd.common.annotations.Command;
import com.todo.activities.domain.ActivityType;
import com.todo.activities.domain.Progress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Command
@Data
public class AddActivityCommand {
    private long userId;
    private long activitiesListId;
    private ActivityType activityType;
    private long award;
    private Progress progress;
    private LocalDateTime statDate;
    private boolean isDone;
}
