package com.todo.activities.domain;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class ActivitiesTest {

    @Test
    public void shouldAddActivity() {
        // given
        Activities activities = new Activities(null);
        ActivityType activityType = new ActivityType(
                "test",
                Duration.ZERO,
                1000
        );
        Activity activity = new Activity(activityType, activities);
        Activity activity1 = new Activity(activityType, activities);
        // when
        activities.addActivity(activity);
        activities.addActivity(activity1);
        // then
        assertEquals( 2, activities.getActivities().size(),
                "List size differs.");
    }

    @Test
    public void shouldntBeAbleToModifyList() {
        // given
        Activities activities = new Activities(null);
        ActivityType activityType = new ActivityType(
                "test",
                Duration.ZERO,
                1000
        );
        Activity activity = new Activity(activityType, activities);
        Activity activity1 = new Activity(activityType, activities);
        // when
        activities.addActivity(activity);
        // then

        assertThrows( UnsupportedOperationException.class, () -> activities.getActivities().add(activity1),
                "Was able to add activity.");
    }

}