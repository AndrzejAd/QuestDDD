package com.todo.activities.domain;

import com.ddd.common.annotations.DomainFactory;

import java.time.Duration;

@DomainFactory
public class ActivityFactory {

    public Activity createActivities(final ActivityType activityType, final ActivitiesList activitiesList) {
        return new Activity(activityType, activitiesList);
    }

}
