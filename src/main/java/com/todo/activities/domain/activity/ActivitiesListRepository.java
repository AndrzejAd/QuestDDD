package com.todo.activities.domain.activity;

import java.util.Optional;

public interface ActivitiesListRepository {
    ActivitiesList save(ActivitiesList activitiesList);
    Optional<ActivitiesList> findById(long id);
}
