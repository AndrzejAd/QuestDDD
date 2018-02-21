package com.todo.activities.infastructure;

import com.todo.activities.domain.ActivityType;
import com.todo.activities.domain.ActivityTypeRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataActivityTypeRepository extends ActivityTypeRepository, JpaRepository<ActivityType, Long> {
}
