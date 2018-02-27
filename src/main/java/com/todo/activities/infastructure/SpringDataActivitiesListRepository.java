package com.todo.activities.infastructure;

import com.todo.activities.domain.activity.ActivitiesList;
import com.todo.activities.domain.activity.ActivitiesListRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataActivitiesListRepository extends ActivitiesListRepository, JpaRepository<ActivitiesList, Long> {
}
