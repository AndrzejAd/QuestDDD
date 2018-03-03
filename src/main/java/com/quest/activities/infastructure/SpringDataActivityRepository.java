package com.quest.activities.infastructure;

import com.quest.activities.domain.activity.Activity;
import com.quest.activities.domain.activity.ActivityRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataActivityRepository extends ActivityRepository, JpaRepository<Activity, Long> {
}
