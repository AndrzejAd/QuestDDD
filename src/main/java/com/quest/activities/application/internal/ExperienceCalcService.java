package com.quest.activities.application.internal;

import com.ddd.common.annotations.DomainService;
import com.quest.activities.domain.activity.ActivitiesList;
import com.quest.activities.domain.activity.Activity;
import com.quest.activities.domain.user.User;

@DomainService
public interface ExperienceCalcService {
    long calculateExperienceGain(Activity activity, ActivitiesList activitiesList, User user);
}
