package com.todo.activities.application;

import com.ddd.common.annotations.DomainService;
import com.todo.activities.domain.Activity;

@DomainService
public interface ExperienceCalcService {
    long calculateExperienceGain(Activity activity);
}
