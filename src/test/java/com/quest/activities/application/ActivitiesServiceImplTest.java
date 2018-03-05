package com.quest.activities.application;

import com.quest.activities.application.commands.AddActivityCommand;
import com.quest.activities.application.commands.CreateNewActivitiesListCommand;
import com.quest.activities.application.commands.FinishActivityCommand;
import com.quest.activities.application.commands.StartActivityCommand;
import com.quest.activities.application.service.ActivitiesServiceImpl;
import com.quest.activities.application.service.exceptions.UserNotFound;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;




@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestEntityManager
@Transactional
public class ActivitiesServiceImplTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ActivitiesServiceImpl activitiesServiceImpl;

    @Autowired
    private AccountFactory accountFactory;

    private User user;
    private ActivityType testActivityType;

    @BeforeEach
    void setUpUser(){
        Account account = accountFactory.createUser("a1190842@nwytg.com", "Poland", "Krk", "Guy",
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
    void shouldAddActivitiesListToUser() {
        //given
        //when
        activitiesServiceImpl.addNewActivitiesListToUser( new CreateNewActivitiesListCommand(user.getId()) );
        //then
        assertEquals( 1, user.getActivitiesList().size(), "ActivitiesList weren't persisted" );
    }

    @Test
    void shouldThrowUserNotFoundException() {
        // given
        // when
        // then
        assertThrows(UserNotFound.class,
                () -> activitiesServiceImpl.addNewActivitiesListToUser( new CreateNewActivitiesListCommand(999) ),
                "Was able to add activities list to non existent user. ");
    }

    @Test
    void shouldAddNewActivityToActivitiesList() {
        //given
        activitiesServiceImpl.addNewActivitiesListToUser( new CreateNewActivitiesListCommand(user.getId()));
        List<ActivitiesList> activitiesList = user.getActivitiesList();

        // when
        activitiesServiceImpl.addActivityToActivityList( new AddActivityCommand(
                activitiesList.get(0).getId(),
                testActivityType.getId(),
                0,
                0
        ));
        // then
        assertEquals( 1, user.getActivitiesList().get(0).getActivities().size(),
                "Activity weren't persisted" );
    }

    @Test
    void shouldSendEmailToUser(){
        //given
        ActivitiesList activitiesList = activitiesServiceImpl.addNewActivitiesListToUser( new CreateNewActivitiesListCommand(user.getId()) );
        Activity activity =
                activitiesServiceImpl.addActivityToActivityList(new AddActivityCommand(activitiesList.getId(), testActivityType.getId(), 0 ,0));
        //when
        activitiesServiceImpl.startActivity(new StartActivityCommand(activity.getId()));
        activitiesServiceImpl.finishActivity(new FinishActivityCommand(activity.getId()));
        //then
        assertTrue(true);
    }

}