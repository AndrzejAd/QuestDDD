package com.quest.activities.application.service;

import com.ddd.common.annotations.ApplicationService;
import com.quest.activities.application.commands.GetNearbyUsersCommand;
import com.quest.activities.application.service.exceptions.UserNotFound;
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

    @Override
    public NearbyQuestersDto getNearbyUsersBasedOnActivities(GetNearbyUsersCommand getNearbyUsersCommand){
        User user = userRepository
                .find(getNearbyUsersCommand.getUserId())
                .orElseThrow(UserNotFound::new);
        return nearbyQuestersFinder.getNearbyUsers(user, radius).dto();
    }
}
