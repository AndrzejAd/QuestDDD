package com.quest.activities.application.service;

import com.quest.activities.application.commands.GetNearbyUsersCommand;
import com.quest.activities.domain.location.dto.NearbyQuestersDto;

public interface IActivitiesQueryService {
    NearbyQuestersDto getNearbyUsersBasedOnActivities(GetNearbyUsersCommand getNearbyUsersCommand);
}
