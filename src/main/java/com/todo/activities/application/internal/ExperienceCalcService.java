package com.todo.activities.application.internal;

import com.ddd.common.annotations.DomainService;
import com.todo.activities.domain.activity.ActivitiesList;
import com.todo.activities.domain.activity.Activity;
import com.todo.activities.domain.user.User;

@DomainService
public interface ExperienceCalcService {
    long calculateExperienceGain(Activity activity, ActivitiesList activitiesList, User user);
}
