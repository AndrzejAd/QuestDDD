package com.todo.activities.application;

import com.ddd.common.annotations.DomainService;
import com.todo.activities.domain.ActivitiesList;
import com.todo.activities.domain.Activity;
import com.todo.activities.domain.User;

@DomainService
public interface ExperienceCalcService {
    long calculateExperienceGain(Activity activity, ActivitiesList activitiesList, User user);
}
