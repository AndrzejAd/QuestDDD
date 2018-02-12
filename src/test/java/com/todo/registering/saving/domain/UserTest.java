package com.todo.registering.saving.domain;

import com.todo.common.validation.InvalidDate;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void shouldCreateUser() {
        new User(new Email("@"),
                new Address("bbbb", "cccc"),
                "test",
                "test",
                LocalDate.now(),
                LocalDate.of(1992, Month.JUNE, 25));
        assertTrue(true);
    }

    @Test
    public void shouldntCreateUserDueToWrongBirthDate() {
        assertThrows(InvalidDate.class, () ->
                new User(new Email("@"),
                        new Address("bbbb", "cccc"),
                        "test",
                        "test",
                        LocalDate.now(),
                        LocalDate.of(1500, Month.JUNE, 25)),
                "User not created due to wrong birthday");
    }

}