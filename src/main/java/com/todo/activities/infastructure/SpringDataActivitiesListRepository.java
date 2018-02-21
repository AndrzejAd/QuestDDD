package com.todo.activities.infastructure;

import com.todo.activities.domain.ActivitiesList;
import com.todo.activities.domain.ActivitiesListRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataActivitiesListRepository extends ActivitiesListRepository, JpaRepository<ActivitiesList, Long> {
}
