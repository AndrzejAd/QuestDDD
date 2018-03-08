package com.quest.activities.domain.location;

import com.ddd.common.annotations.AggregateRoot;
import com.ddd.common.validation.Contract;
import com.ddd.common.validation.ContractBroken;
import com.quest.activities.domain.activity.Activity;
import com.quest.activities.domain.activity.ActivityType;
import com.quest.activities.domain.location.dto.Location;
import com.quest.activities.domain.location.dto.NearbyQuestersDto;
import com.quest.activities.domain.user.User;
import lombok.*;

import java.util.*;
import java.util.stream.Collectors;

@AggregateRoot
@EqualsAndHashCode @AllArgsConstructor @ToString
public class NearbyQuesters {
    @Getter
    private Location location;
    private Collection<User> nearbyQuesters;

    /**
     * Ensures there can be only one user in collection.
     * @param user
     */
    public void addNearbyUser(User user) {
        Contract.notNull(user);
        if ( nearbyQuesters.contains(user) ) throw new UserIsAlreadyInNearbyQuesters();
        nearbyQuesters.add(user);
    }

    public Optional<User> getUserWithMaxLevel(){
        return nearbyQuesters.stream()
                .sorted(Comparator.comparingInt(User::getLevel))
                .findFirst();
    }

    public Map<Integer, List<User>> getNearbyQuestersByLevel(){
        return nearbyQuesters.stream()
                .collect(Collectors.groupingBy(User::getLevel));
    }

    public Collection<User> getNearbyQuesters() {
        return Collections.unmodifiableCollection(nearbyQuesters);
    }

    public NearbyQuestersDto dto(){
        return NearbyQuestersDto.builder()
                .location(location)
                .nearbyQuesters(nearbyQuesters)
                .build();
    }

    private class UserIsAlreadyInNearbyQuesters extends ContractBroken{}


}
