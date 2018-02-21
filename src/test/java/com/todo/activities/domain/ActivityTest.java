package com.todo.activities.domain;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class ActivityTest {

    @Test
    void shouldntStartActivity() {
        // given
        ActivitiesList activitiesList = null;
        ActivityType activityType = new ActivityType(
                "test",
                Duration.ZERO,
                1000
        );
        Activity activity = new Activity(activityType, activitiesList);
        // when
        activity.startActivity();
        // then
        assertThrows( Activity.ActivityAlreadyStarted.class, activity::startActivity,
                "Since activity has already started, exception is being thrown.");
    }

    @Test
    void shouldntFinishActivity() {
        // given
        ActivitiesList activitiesList = null;
        ActivityType activityType = new ActivityType(
                "test",
                Duration.ZERO,
                1000
        );
        Activity activity = new Activity(activityType, activitiesList);
        // when
        // then
        assertThrows( Activity.ActivityNotStarted.class, activity::finishActivity,
                "Since activity hasn't  started, exception is being thrown.");
    }
}