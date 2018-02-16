package com.todo.registering.saving.domain;

import com.ddd.common.validation.InvalidDate;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    @Test
    public void shouldCreateUser() {
        new Account(new Email("@"),
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
                new Account(new Email("@"),
                        new Address("bbbb", "cccc"),
                        "test",
                        "test",
                        LocalDate.now(),
                        LocalDate.of(1500, Month.JUNE, 25)),
                "Account not created due to wrong birthday");
    }

}