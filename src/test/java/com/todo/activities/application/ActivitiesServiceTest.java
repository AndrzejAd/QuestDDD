package com.todo.activities.application;

import com.todo.activities.application.commands.CreateNewActivitiesListCommand;
import com.todo.activities.domain.ActivitiesRepository;
import com.todo.activities.domain.ActivityType;
import com.todo.activities.domain.User;
import com.todo.registering.saving.domain.Account;
import com.todo.registering.saving.domain.AccountFactory;
import com.todo.registering.saving.domain.Address;
import com.todo.registering.saving.domain.Email;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;

import static org.junit.jupiter.api.Assertions.*;




@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestEntityManager
@Transactional
public class ActivitiesServiceTest {

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
        activitiesService.addActivitiesListToUser( new CreateNewActivitiesListCommand(user.getId()));
        assertEquals( 1, user.getActivities().size(), "Activities weren't persisted" );
    }
}