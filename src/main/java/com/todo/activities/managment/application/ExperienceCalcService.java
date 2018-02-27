package com.todo.activities.managment.application;

import com.ddd.common.annotations.DomainService;
import com.todo.activities.managment.domain.ActivitiesList;
import com.todo.activities.managment.domain.Activity;
import com.todo.activities.managment.domain.User;

@DomainService
public interface ExperienceCalcService {
    long calculateExperienceGain(Activity activity, ActivitiesList activitiesList, User user);
}
