package com.quest.activities.domain.location.dto;

import com.quest.activities.domain.user.User;
import lombok.Builder;
import lombok.Value;

import java.util.Collection;

@Value @Builder
public class NearbyQuestersDto{
    private final Location location;
    private final Collection<User> nearbyQuesters;
}
