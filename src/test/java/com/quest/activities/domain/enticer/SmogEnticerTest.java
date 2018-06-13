package com.quest.activities.domain.enticer;

import name.falgout.jeffrey.testing.junit5.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.core.task.TaskExecutor;

@ExtendWith(MockitoExtension.class)
class SmogEnticerTest {
    private SmogEnticer smogEnticer;
    @Mock
    private TaskExecutor taskExecutor;

    @Test
    void enticeActivityMultiplier() {
        // given
        // when
        // then
    }

    @Test
    void getIdsOfStations() {
        // given
        smogEnticer = new SmogEnticer(null, taskExecutor);
        // when
        System.out.println(smogEnticer.enticeActivityMultiplier());
        // then
    }
}