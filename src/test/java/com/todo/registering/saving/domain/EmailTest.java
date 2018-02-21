package com.todo.registering.saving.domain;

import com.ddd.common.validation.RegexMatchFailed;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class EmailTest {

    @Test
    void shouldntThrowRegexException(){
        new Email("a@b");
    }

    @Test
    void shouldThrowRegexException(){
        assertThrows(RegexMatchFailed.class, () -> new Email("abxde"));
    }

}