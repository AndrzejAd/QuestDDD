package com.todo.activities.infastructure;

import com.todo.activities.domain.activity.Activity;
import com.todo.activities.domain.activity.ActivityRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataActivityRepository extends ActivityRepository, JpaRepository<Activity, Long> {
}
