package com.quest.activities.application.internal;

import com.quest.activities.domain.activity.ActivitiesList;
import com.quest.activities.domain.activity.Activity;
import com.quest.activities.domain.activity.ActivityType;
import com.quest.activities.domain.location.NearbyQuesters;
import com.quest.activities.domain.location.NearbyQuestersFinder;
import com.quest.activities.domain.user.User;
import name.falgout.jeffrey.testing.junit5.MockitoExtension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.MockitoJUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureTestEntityManager
@Transactional
class ActivitiesBasedNearbyQuestersFinderTest {

    @Autowired
    private NearbyQuestersFinder nearbyQuestersFinder;

    @Autowired
    private TestEntityManager testEntityManager;

    private ArrayList<User> users;
    private ArrayList<ActivitiesList> activitiesLists;
    private ArrayList<Activity> activities;
    private ActivityType activityType;

    /**
     * 3 users, 3 activities lists,
     */
    @BeforeEach
    private void setUsersAndActivities(){
        users = new ArrayList<>();
        activitiesLists = new ArrayList<>();
        activities = new ArrayList<>();
        activityType = new ActivityType("", Duration.ZERO, 1000);
        testEntityManager.persist(activityType);
        users.add(testEntityManager.persist(new User()));
        users.add(testEntityManager.persist(new User()));
        users.add(testEntityManager.persist(new User()));
        activitiesLists.add( testEntityManager.persist(new ActivitiesList(users.get(0))));
        activitiesLists.add( testEntityManager.persist(new ActivitiesList(users.get(1))));
        activitiesLists.add( testEntityManager.persist(new ActivitiesList(users.get(2))));
        users.get(0).addActivities(activitiesLists.get(0));
        users.get(1).addActivities(activitiesLists.get(1));
        users.get(2).addActivities(activitiesLists.get(2));
    }

    @Test
    void shouldFindAllNearbyUsersInAcceptableRadius() {
        // given
        activities.add( testEntityManager.persist(new Activity(activityType, activitiesLists.get(0), 50, 50)));
        activities.add( testEntityManager.persist(new Activity(activityType, activitiesLists.get(1), 50, 50)));
        activities.add( testEntityManager.persist(new Activity(activityType, activitiesLists.get(2), 50, 50)));
        activitiesLists.get(0).addActivity(activities.get(0));
        activitiesLists.get(1).addActivity(activities.get(1));
        activitiesLists.get(2).addActivity(activities.get(2));
        // when
        NearbyQuesters nearbyQuesters = nearbyQuestersFinder.getNearbyUsers(users.get(0), 50);
        // then
        assertEquals(3, nearbyQuesters.getNearbyQuesters().size(), "User wasnt added as friend.");
    }

    @Test
    void shouldntFindUsersOutsideAcceptableRadius() {
        // given
        activities.add( testEntityManager.persist(new Activity(activityType, activitiesLists.get(0), 50, 50)));
        activities.add( testEntityManager.persist(new Activity(activityType, activitiesLists.get(1), 50, 50)));
        activities.add( testEntityManager.persist(new Activity(activityType, activitiesLists.get(2), 15, 50)));
        activitiesLists.get(0).addActivity(activities.get(0));
        activitiesLists.get(1).addActivity(activities.get(1));
        activitiesLists.get(2).addActivity(activities.get(2));
        // when
        NearbyQuesters nearbyQuesters = nearbyQuestersFinder.getNearbyUsers(users.get(0), 50);
        // then
        assertEquals(2, nearbyQuesters.getNearbyQuesters().size(), "User wasnt added as friend.");
    }

}