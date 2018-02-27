package com.todo.activities.managment.infastructure;

import com.todo.activities.managment.domain.ActivityType;
import com.todo.activities.managment.domain.ActivityTypeRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataActivityTypeRepository extends ActivityTypeRepository, JpaRepository<ActivityType, Long> {
}
