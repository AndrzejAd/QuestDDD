package com.quest.activities.domain.location;

import com.ddd.common.annotations.AggregateRoot;
import com.ddd.common.validation.Contract;
import com.quest.activities.domain.user.User;
import com.sun.javafx.UnmodifiableArrayList;
import lombok.*;

import java.util.*;

@AggregateRoot
@EqualsAndHashCode @AllArgsConstructor
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
        nearbyQuesters.add(user);
    }

    public Collection<User> getNearbyQuesters() {
        return Collections.unmodifiableCollection(nearbyQuesters);
    }

}
