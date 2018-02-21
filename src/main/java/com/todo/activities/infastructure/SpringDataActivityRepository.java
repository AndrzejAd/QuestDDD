package com.todo.activities.infastructure;

import com.todo.activities.domain.Activity;
import com.todo.activities.domain.ActivityRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataActivityRepository extends ActivityRepository, JpaRepository<Activity, Long> {
}
