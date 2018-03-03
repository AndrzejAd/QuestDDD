package com.quest.activities.domain.location;

import com.ddd.common.annotations.AggregateRoot;
import com.quest.activities.domain.user.User;
import lombok.Getter;

import java.util.List;

@AggregateRoot
@Getter
public class NearbyQuesters {
    private Location location;
    private List<User> nearbyQuesters;

}
