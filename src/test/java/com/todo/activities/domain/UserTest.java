package com.todo.activities.domain;

import com.todo.activities.application.ActivitiesService;
import com.todo.activities.application.commands.AddActivityCommand;
import com.todo.activities.application.commands.CreateNewActivitiesListCommand;
import com.todo.activities.application.commands.FinishActivityCommand;
import com.todo.activities.application.commands.StartActivityCommand;
import com.todo.registering.saving.domain.Account;
import com.todo.registering.saving.domain.AccountFactory;
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
import java.util.ArrayList;

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
    private ActivitiesService activitiesService;

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
    void shouldSumTotalExperience() {
        // given
        activitiesService.addNewActivitiesListToUser(new CreateNewActivitiesListCommand(user.getId()));
        activitiesService.addNewActivitiesListToUser(new CreateNewActivitiesListCommand(user.getId()));
        ActivitiesList activitiesList = user.getActivitiesList().get(0);
        ActivitiesList activitiesList1 = user.getActivitiesList().get(1);
        // when
        Activity activity1 = activitiesService.addActivityToActivityList(new AddActivityCommand(
                activitiesList.getId(),
                "test",
                Duration.ZERO,
                1500));
        Activity activity2 =  activitiesService.addActivityToActivityList(new AddActivityCommand(
                activitiesList.getId(),
                "test1",
                Duration.ZERO,
                500));
        Activity activity3 = activitiesService.addActivityToActivityList(new AddActivityCommand(
                activitiesList1.getId(),
                "test",
                Duration.ZERO,
                1500));
        Activity activity4 =  activitiesService.addActivityToActivityList(new AddActivityCommand(
                activitiesList1.getId(),
                "test1",
                Duration.ZERO,
                500));
        activitiesService.startActivity(new StartActivityCommand( activity1.getId() ));
        activitiesService.finishActivity(new FinishActivityCommand( activity1.getId()));
        activitiesService.startActivity(new StartActivityCommand( activity2.getId() ));
        activitiesService.finishActivity(new FinishActivityCommand( activity2.getId()));
        activitiesService.startActivity(new StartActivityCommand( activity3.getId() ));
        activitiesService.finishActivity(new FinishActivityCommand( activity3.getId()));
        activitiesService.startActivity(new StartActivityCommand( activity4.getId() ));
        activitiesService.finishActivity(new FinishActivityCommand( activity4.getId()));
        // then
        assertEquals( 4000, user.getUserExperience(), "User total experience differs.");
    }


    @Test
    void shouldOnlyFinishedActivities() {
        // given
        activitiesService.addNewActivitiesListToUser(new CreateNewActivitiesListCommand(user.getId()));
        activitiesService.addNewActivitiesListToUser(new CreateNewActivitiesListCommand(user.getId()));
        ActivitiesList activitiesList = user.getActivitiesList().get(0);
        ActivitiesList activitiesList1 = user.getActivitiesList().get(1);
        // when
        Activity activity1 = activitiesService.addActivityToActivityList(new AddActivityCommand(
                activitiesList.getId(),
                "test",
                Duration.ZERO,
                1500));
        Activity activity2 =  activitiesService.addActivityToActivityList(new AddActivityCommand(
                activitiesList.getId(),
                "test1",
                Duration.ZERO,
                500));
        Activity activity3 = activitiesService.addActivityToActivityList(new AddActivityCommand(
                activitiesList1.getId(),
                "test",
                Duration.ZERO,
                1500));
        Activity activity4 =  activitiesService.addActivityToActivityList(new AddActivityCommand(
                activitiesList1.getId(),
                "test1",
                Duration.ZERO,
                500));
        activitiesService.startActivity(new StartActivityCommand( activity1.getId() ));
        activitiesService.startActivity(new StartActivityCommand( activity2.getId() ));
        activitiesService.startActivity(new StartActivityCommand( activity3.getId() ));
        activitiesService.startActivity(new StartActivityCommand( activity4.getId() ));
        activitiesService.finishActivity(new FinishActivityCommand( activity1.getId()));
        activitiesService.finishActivity(new FinishActivityCommand( activity3.getId()));
        // then
        assertEquals( 3000, user.getUserExperience(), "User total experience differs.");
    }

}
