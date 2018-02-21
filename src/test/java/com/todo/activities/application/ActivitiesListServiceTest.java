package com.todo.activities.application;

import com.todo.activities.application.commands.AddActivityCommand;
import com.todo.activities.application.commands.CreateNewActivitiesListCommand;
import com.todo.activities.domain.ActivitiesList;
import com.todo.activities.domain.User;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;




@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestEntityManager
@Transactional
public class ActivitiesListServiceTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ActivitiesService activitiesService;

    @Autowired
    private AccountFactory accountFactory;

    private User user;

    @BeforeEach
    public void setUpUser(){
        Account account = accountFactory.createUser("test@test", "Poland", "Krk", "Guy",
                "Strong",
                LocalDate.of(1990, Month.APRIL, 25).toEpochDay());
        testEntityManager.persist(account);
        testEntityManager.flush();
        testEntityManager.clear();
        user = testEntityManager.find(User.class, testEntityManager.getId(account));
    }

    @Test
    public void shouldAddActivitiesListToUser() {
        //given
        //when
        activitiesService.addNewActivitiesListToUser( new CreateNewActivitiesListCommand(user.getId()));
        //then
        assertEquals( 1, user.getActivitiesList().size(), "ActivitiesList weren't persisted" );
    }

    @Test
    public void shouldAddNewActivityToActivitiesList() {
        //given
        activitiesService.addNewActivitiesListToUser( new CreateNewActivitiesListCommand(user.getId()));
        List<ActivitiesList> activitiesList = user.getActivitiesList();
        // when
        activitiesService.addActivityToActivityList( new AddActivityCommand(
                activitiesList.get(0).getId(),
                "test",
                Duration.ZERO,
                1000
        ));
        // then
        assertEquals( 1, user.getActivitiesList().get(0).getActivities().size(),
                "Activity weren't persisted" );
    }

}