package com.todo.activities.managment.infastructure;

import com.todo.activities.managment.domain.ActivitiesList;
import com.todo.activities.managment.domain.ActivitiesListRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataActivitiesListRepository extends ActivitiesListRepository, JpaRepository<ActivitiesList, Long> {
}
