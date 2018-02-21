package com.todo.activities.domain;

import com.todo.registering.saving.domain.Account;
import com.todo.registering.saving.domain.AccountFactory;
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
        Activity activity = new Activity(testActivityType, testActivitiesList);
        Activity activity1 = new Activity(testActivityType, testActivitiesList);
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
        Activity activity = new Activity(testActivityType, testActivitiesList);
        Activity activity1 = new Activity(testActivityType, testActivitiesList);
        // when
        testActivitiesList.addActivity(activity);
        testEntityManager.persist(testActivityType);
        testEntityManager.persist(testActivitiesList);
        testActivitiesList.addActivity(activity1);
        testEntityManager.persist(testActivitiesList);
        // then
        ActivitiesList activitiesList = testEntityManager.find(ActivitiesList.class, testActivitiesList.getId());
        assertEquals( 2, activitiesList.getActivities().size(),
                "Objects weren't persisted properly");
    }

    @Test
    void shouldntBeAbleToModifyList() {
        // given
        ActivitiesList activitiesList = new ActivitiesList(user);
        Activity activity = new Activity(testActivityType, activitiesList);
        Activity activity1 = new Activity(testActivityType, activitiesList);
        // when
        activitiesList.addActivity(activity);
        // then
        assertThrows( UnsupportedOperationException.class, () -> activitiesList.getActivities().add(activity1),
                "Was able to add activity.");
    }

    @Test
    void shouldSumExperience(){
        // given
        ActivitiesList activitiesList = new ActivitiesList(user);
        Activity activity = new Activity(testActivityType, activitiesList);
        Activity activity1 = new Activity(testActivityType, activitiesList);
        activity.startActivity();
        activity.finishActivity();
        activity1.startActivity();
        activity1.finishActivity();
        testEntityManager.persist(testActivityType);
        testEntityManager.persist(activitiesList);
        testEntityManager.persist(activity);
        testEntityManager.persist(activity1);
        testEntityManager.flush();
        testEntityManager.clear();
        // when
        activitiesList = testEntityManager.find(ActivitiesList.class, activitiesList.getId());
        // then
        assertEquals( 2000, activitiesList.getTotalExperience(),
                "Total experience differs from what was expected");
    }


}