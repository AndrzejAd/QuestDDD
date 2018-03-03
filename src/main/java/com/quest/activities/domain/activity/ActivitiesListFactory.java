package com.quest.activities.domain.activity;

import com.ddd.common.annotations.DomainFactory;
import com.quest.activities.domain.user.User;

@DomainFactory
public class ActivitiesListFactory {

    public ActivitiesList createActivities(final User user ){
        return new ActivitiesList(user);
    }

}
