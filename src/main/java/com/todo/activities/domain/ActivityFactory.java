package com.todo.activities.domain;

import com.ddd.common.annotations.DomainFactory;

import java.time.Duration;

@DomainFactory
public class ActivityFactory {

    public Activity createActivities(
            final String description,
            final Duration expectedDuration,
            final long baseAward,
            final ActivitiesList activitiesList) {
        ActivityType activityType = new ActivityType(description, expectedDuration, baseAward);
        return new Activity(activityType, activitiesList);
    }

}
