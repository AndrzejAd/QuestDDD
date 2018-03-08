package com.quest.activities.application.service;

import com.ddd.common.annotations.ApplicationService;
import com.quest.activities.application.queries.GetNearbyUsersQuery;
import com.quest.activities.application.queries.GetUserActivitiesListQuery;
import com.quest.activities.application.service.exceptions.ActivitiesListNotFound;
import com.quest.activities.application.service.exceptions.UserNotFound;
import com.quest.activities.domain.activity.ActivitiesList;
import com.quest.activities.domain.activity.ActivitiesListRepository;
import com.quest.activities.domain.activity.dto.ActivitiesListDto;
import com.quest.activities.domain.location.NearbyQuestersFinder;
import com.quest.activities.domain.location.dto.NearbyQuestersDto;
import com.quest.activities.domain.user.User;
import com.quest.activities.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;

@ApplicationService
@RequiredArgsConstructor
public class ActivitiesQueryService implements IActivitiesQueryService {
    private final double radius = 50;
    private final UserRepository userRepository;
    private final NearbyQuestersFinder nearbyQuestersFinder;
    private final ActivitiesListRepository activitiesListRepository;

    @Override
    public NearbyQuestersDto getNearbyUsersBasedOnActivities(GetNearbyUsersQuery getNearbyUsersQuery){
        User user = userRepository
                .find(getNearbyUsersQuery.getUserId())
                .orElseThrow(UserNotFound::new);
        return nearbyQuestersFinder.getNearbyUsers(user, radius).dto();
    }

    @Override
    public ActivitiesListDto getUserActivitiesList(GetUserActivitiesListQuery getUserActivitiesListQuery) {
        ActivitiesList activitiesList = activitiesListRepository
                .findById(getUserActivitiesListQuery.getActivitiesListId())
                .orElseThrow(ActivitiesListNotFound::new);
        return activitiesList.dto();
    }
}
