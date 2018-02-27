package com.todo.activities.infastructure;

import com.todo.activities.domain.activity.ActivityType;
import com.todo.activities.domain.activity.ActivityTypeRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataActivityTypeRepository extends ActivityTypeRepository, JpaRepository<ActivityType, Long> {
}
