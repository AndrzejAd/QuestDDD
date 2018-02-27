package com.todo.activities.domain.activity;

import com.ddd.common.annotations.DomainFactory;
import com.todo.activities.domain.user.User;

@DomainFactory
public class ActivitiesListFactory {

    public ActivitiesList createActivities(final User user ){
        return new ActivitiesList(user);
    }

}
