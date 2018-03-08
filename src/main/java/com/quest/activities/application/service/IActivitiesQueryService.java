package com.quest.activities.application.service;

import com.quest.activities.application.queries.GetNearbyUsersQuery;
import com.quest.activities.application.queries.GetUserActivitiesListQuery;
import com.quest.activities.domain.activity.dto.ActivitiesListDto;
import com.quest.activities.domain.location.dto.NearbyQuestersDto;

public interface IActivitiesQueryService {
    NearbyQuestersDto getNearbyUsersBasedOnActivities(GetNearbyUsersQuery getNearbyUsersQuery);
    ActivitiesListDto getUserActivitiesList(GetUserActivitiesListQuery getUserActivitiesListQuery);
}
