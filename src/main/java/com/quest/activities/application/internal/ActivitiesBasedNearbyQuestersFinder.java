package com.quest.activities.application.internal;

import com.ddd.common.annotations.DomainService;
import com.quest.activities.domain.location.NearbyQuesters;
import com.quest.activities.domain.user.User;
import com.quest.activities.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;


@DomainService
@RequiredArgsConstructor
public class ActivitiesBasedNearbyQuestersFinder implements NearbyQuestersFinder {
    private final UserRepository userRepository;

    @Override
    public NearbyQuesters getNearbyUsers(User user) {
        System.out.println(userRepository);
        return null;
    }
}
