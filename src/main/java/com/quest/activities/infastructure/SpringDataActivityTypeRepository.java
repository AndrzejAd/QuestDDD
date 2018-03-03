package com.quest.activities.infastructure;

import com.quest.activities.domain.activity.ActivityType;
import com.quest.activities.domain.activity.ActivityTypeRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataActivityTypeRepository extends ActivityTypeRepository, JpaRepository<ActivityType, Long> {
}
