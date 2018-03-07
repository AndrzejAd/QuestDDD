package com.quest.activities.application.queries;

import com.ddd.common.annotations.Command;
import lombok.Value;

@Command
@Value
public class GetNearbyUsersCommand {
    private long userId;
}
