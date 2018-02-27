package com.todo.activities.domain.activity;

import java.util.Optional;

public interface ActivityTypeRepository {
    ActivityType save(ActivityType activityType);
    Optional<ActivityType> findById( long activityTypeId );
}
