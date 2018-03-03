package com.quest.activities.application.commands;

import com.ddd.common.annotations.Command;
import lombok.Value;

@Command
@Value
public class GetNearbyUsersCommand {
    private long userId;
}
