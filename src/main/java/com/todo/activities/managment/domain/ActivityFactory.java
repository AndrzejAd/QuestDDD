package com.todo.activities.managment.domain;

import com.ddd.common.annotations.DomainFactory;

@DomainFactory
public class ActivityFactory {

    public Activity createActivities(final ActivityType activityType, final ActivitiesList activitiesList,
                                     final long longitude, final long latitude) {
        return new Activity(activityType, activitiesList, longitude, latitude);
    }

}
