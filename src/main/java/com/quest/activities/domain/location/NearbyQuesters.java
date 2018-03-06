package com.quest.activities.domain.location;

import com.ddd.common.annotations.AggregateRoot;
import com.ddd.common.validation.Contract;
import com.ddd.common.validation.ContractBroken;
import com.quest.activities.domain.user.User;
import com.sun.javafx.UnmodifiableArrayList;
import lombok.*;

import java.util.*;

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

    public Collection<User> getNearbyQuesters() {
        return Collections.unmodifiableCollection(nearbyQuesters);
    }

    private class UserIsAlreadyInNearbyQuesters extends ContractBroken{}

}
