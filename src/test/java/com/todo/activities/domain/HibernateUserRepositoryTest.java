package com.todo.activities.domain;

import com.todo.activities.infastructure.HibernateUserRepository;
import com.todo.registering.saving.appplication.RegisterUserService;
import com.todo.registering.saving.appplication.commands.CreateUserCommand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class HibernateUserRepositoryTest {
    private final String testEmailAddress = "z2370531@mvrht.net";

    @Autowired
    private HibernateUserRepository hibernateUserRepository;

    @Autowired
    private RegisterUserService registerUserService;

    @Test
    void userShouldExist() {
        // given
        CreateUserCommand createUserCommand = new CreateUserCommand(
                testEmailAddress,
                "Poland",
                "Krk",
                "Guy",
                "Strong",
                LocalDate.of(1990, Month.APRIL, 25).toEpochDay()
        );
        // when
        registerUserService.registerUser(createUserCommand);
        // then
        assertTrue(hibernateUserRepository.doesUserExist(1));
    }

    @Test
    void userShouldntExist() {
        // given
        CreateUserCommand createUserCommand = new CreateUserCommand(
                testEmailAddress,
                "Poland",
                "Krk",
                "Guy",
                "Strong",
                LocalDate.of(1990, Month.APRIL, 25).toEpochDay()
        );
        // when
        registerUserService.registerUser(createUserCommand);
        // then
        assertFalse(hibernateUserRepository.doesUserExist(2));
    }

}