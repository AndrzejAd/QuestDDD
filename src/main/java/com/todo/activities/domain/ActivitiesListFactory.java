package com.todo.activities.domain;

import com.ddd.common.annotations.DomainFactory;

@DomainFactory
public class ActivitiesListFactory {

    public ActivitiesList createActivities(final User user ){
        return new ActivitiesList(user);
    }

}
