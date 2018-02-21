package com.todo.activities.domain;

import com.ddd.common.annotations.DomainFactory;

@DomainFactory
public class ActivitiesFactory {

    public Activities createActivities(final User user ){
        return new Activities(user);
    }

}
