package com.quest.activities.application.internal;

import com.quest.activities.domain.location.NearbyQuestersFinder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ActivitiesBasedNearbyQuestersFinderTest {

    @Autowired
    private NearbyQuestersFinder nearbyQuestersFinder;

    @Test
    void getNearbyUsers() {
        // given
        // when
        // then
        nearbyQuestersFinder.getNearbyUsers(null);
    }
}