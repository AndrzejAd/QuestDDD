package com.todo.activities.domain;

import java.util.Optional;

public interface ActivityRepository {
    Activity save(Activity activity);
    Optional<Activity> findById(long activityId);
}
