package com.quest.activities.domain.location;

import com.ddd.common.annotations.AggregateRoot;
import com.ddd.common.validation.Contract;
import com.quest.activities.domain.user.User;
import com.sun.javafx.UnmodifiableArrayList;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

@AggregateRoot
@NoArgsConstructor @EqualsAndHashCode
public class NearbyQuesters {
    @Getter
    private Location location;
    private Set<User> nearbyQuesters;

    public NearbyQuesters(Location location) {
        this.location = location;
        this.nearbyQuesters = new HashSet<>();
    }

    /**
     * Ensures there can be only one user in collection.
     * @param user
     */
    public void addNearbyUser(User user) {
        Contract.notNull(user);
        nearbyQuesters.add(user);
    }

    public Set<User> getNearbyQuesters() {
        return Collections.unmodifiableSet(nearbyQuesters);
    }

}
