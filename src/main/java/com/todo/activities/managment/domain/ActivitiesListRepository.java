package com.todo.activities.managment.domain;

import java.util.Optional;

public interface ActivitiesListRepository {
    ActivitiesList save(ActivitiesList activitiesList);
    Optional<ActivitiesList> findById(long id);
}
