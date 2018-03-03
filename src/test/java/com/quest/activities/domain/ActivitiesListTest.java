package com.quest.activities.domain;

import com.quest.activities.domain.activity.ActivitiesList;
import com.quest.activities.domain.activity.Activity;
import com.quest.activities.domain.activity.ActivityType;
import com.quest.activities.domain.user.User;
import com.quest.registering.saving.domain.Account;
import com.quest.registering.saving.domain.AccountFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestEntityManager
@Transactional
public class ActivitiesListTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private AccountFactory accountFactory;

    private User user;
    private ActivityType testActivityType;

    @BeforeEach
    void setUpUser(){
        Account account = accountFactory.createUser("test@test", "Poland", "Krk", "Guy",
                "Strong",
                LocalDate.of(1990, Month.APRIL, 25).toEpochDay());
        testEntityManager.persist(account);
        testEntityManager.flush();
        testEntityManager.clear();
        user = testEntityManager.find(User.class, testEntityManager.getId(account));
        testActivityType = new ActivityType(
                "test",
                Duration.ZERO,
                1000
        );
    }

    @Test
    void shouldAddActivity() {
        // given
        ActivitiesList testActivitiesList = new ActivitiesList(user);
        Activity activity = new Activity(testActivityType, testActivitiesList, 0, 0);
        Activity activity1 = new Activity(testActivityType, testActivitiesList, 0, 0);
        testEntityManager.persist(testActivitiesList);
        testEntityManager.persist(testActivityType);
        testEntityManager.persist(activity);
        testEntityManager.persist(activity1);
        // when
        testEntityManager.flush();
        testEntityManager.clear();
        testActivitiesList = testEntityManager.find(ActivitiesList.class, testActivitiesList.getId());
        // then
        assertEquals( 2, testActivitiesList.getActivities().size(),
                "List size differs.");
    }

    @Test
    void shouldPersistActivityViaActivities() {
        // given
        ActivitiesList testActivitiesList = new ActivitiesList(user);
        Activity activity = new Activity(testActivityType, testActivitiesList, 0, 0);
        // when
        testActivitiesList.addActivity(activity);
        testEntityManager.persist(testActivityType);
        testEntityManager.persist(testActivitiesList);
        // then
        assertNotNull( testEntityManager.find(Activity.class, activity.getId()), "Activity weren't persisted");

    }

    @Test
    void shouldntBeAbleToModifyList() {
        // given
        ActivitiesList activitiesList = new ActivitiesList(user);
        Activity activity = new Activity(testActivityType, activitiesList, 0, 0);
        Activity activity1 = new Activity(testActivityType, activitiesList, 0, 0);
        // when
        activitiesList.addActivity(activity);
        // then
        assertThrows( UnsupportedOperationException.class, () -> activitiesList.getActivities().add(activity1),
                "Was able to add activity.");
    }


}