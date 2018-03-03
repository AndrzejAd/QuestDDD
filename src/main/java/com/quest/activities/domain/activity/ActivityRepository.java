package com.quest.activities.domain.activity;

import java.util.Collection;
import java.util.Optional;

public interface ActivityRepository {
    Activity save(Activity activity);
    Optional<Activity> findById(long activityId);
    Collection<Activity> findAll();
}
