package com.quest.accounts.saving.appplication;

import com.quest.accounts.saving.appplication.commands.CreateUserCommand;
import com.quest.accounts.saving.appplication.service.RegisterUserService;
import com.quest.accounts.saving.infastructure.SpringDataUserRepository;
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
public class RegisterAccountServiceTest {
    private final String testEmailAddress = "z2370531@mvrht.net";

    @Autowired
    private RegisterUserService registerUserService;

    @Autowired
    private SpringDataUserRepository springDataUserRepository;

    @Test
    void registerUser() {
        // given
        long dbSize = springDataUserRepository.count();
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
        //then
        //no exception
        assertEquals(dbSize + 1, springDataUserRepository.count(), "Account was saved");
    }
}