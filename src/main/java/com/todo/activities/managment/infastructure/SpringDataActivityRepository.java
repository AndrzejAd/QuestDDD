package com.todo.activities.managment.infastructure;

import com.todo.activities.managment.domain.Activity;
import com.todo.activities.managment.domain.ActivityRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataActivityRepository extends ActivityRepository, JpaRepository<Activity, Long> {
}
