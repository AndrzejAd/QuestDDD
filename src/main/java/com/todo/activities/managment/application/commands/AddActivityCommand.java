package com.todo.activities.managment.application.commands;

import com.ddd.common.annotations.Command;
import com.todo.activities.managment.domain.Progress;
import lombok.Value;

@Command
@Value
public class AddActivityCommand {
    private long activitiesListId;
    private long activityTypeId;
    private long longitude;
    private long latitude;
}
