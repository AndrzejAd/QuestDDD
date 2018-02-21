package com.todo.activities.infastructure;

import com.todo.activities.domain.Activities;
import com.todo.activities.domain.ActivitiesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataActivitiesRepository extends ActivitiesRepository, JpaRepository<Activities, Long> {
}
