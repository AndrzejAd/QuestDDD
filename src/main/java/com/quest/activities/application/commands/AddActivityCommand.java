package com.quest.activities.application.commands;

import com.ddd.common.annotations.Command;
import lombok.Value;

@Command
@Value
public class AddActivityCommand {
    private long activitiesListId;
    private long activityTypeId;
    private double latitude;
    private double longitude;
}
