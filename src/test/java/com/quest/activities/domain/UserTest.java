package com.quest.activities.domain;

import com.quest.activities.application.service.ActivitiesServiceImpl;
import com.quest.activities.application.commands.AddActivityCommand;
import com.quest.activities.application.commands.CreateNewActivitiesListCommand;
import com.quest.activities.application.commands.FinishActivityCommand;
import com.quest.activities.application.commands.StartActivityCommand;
import com.quest.activities.domain.activity.ActivitiesList;
import com.quest.activities.domain.activity.Activity;
import com.quest.activities.domain.activity.ActivityType;
import com.quest.activities.domain.user.User;
import com.quest.registering.saving.domain.Account;
import com.quest.registering.saving.domain.AccountFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestEntityManager
@Transactional
class UserTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private AccountFactory accountFactory;

    @Autowired
    private ActivitiesServiceImpl activitiesServiceImpl;

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
        testEntityManager.persist(testActivityType);
    }

    @Test
    @Disabled
    void shouldSumExperience() {
        // given
        activitiesServiceImpl.addNewActivitiesListToUser(new CreateNewActivitiesListCommand(user.getId()));
        activitiesServiceImpl.addNewActivitiesListToUser(new CreateNewActivitiesListCommand(user.getId()));
        ActivitiesList activitiesList = user.getActivitiesList().get(0);
        ActivitiesList activitiesList1 = user.getActivitiesList().get(1);
        Activity activity1 = activitiesServiceImpl.addActivityToActivityList(new AddActivityCommand(
                activitiesList.getId(),
                testActivityType.getId(),
                0, 0));
        Activity activity2 =  activitiesServiceImpl.addActivityToActivityList(new AddActivityCommand(
                activitiesList.getId(),
                testActivityType.getId(),
                0, 0));
        Activity activity3 = activitiesServiceImpl.addActivityToActivityList(new AddActivityCommand(
                activitiesList1.getId(),
                testActivityType.getId(),
                0, 0));
        // when
        activitiesServiceImpl.startActivity( new StartActivityCommand(activity1.getId()));
        activitiesServiceImpl.finishActivity( new FinishActivityCommand(activity1.getId()));
        activitiesServiceImpl.startActivity( new StartActivityCommand(activity2.getId()));
        activitiesServiceImpl.finishActivity( new FinishActivityCommand(activity2.getId()));
        activitiesServiceImpl.startActivity( new StartActivityCommand(activity3.getId()));
        activitiesServiceImpl.finishActivity( new FinishActivityCommand(activity3.getId()));
        // then
        assertEquals( 19000, user.getUserExperience(), "User total experience differs.");
    }


}
