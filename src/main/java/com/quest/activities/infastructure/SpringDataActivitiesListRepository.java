package com.quest.activities.infastructure;

import com.quest.activities.domain.activity.ActivitiesList;
import com.quest.activities.domain.activity.ActivitiesListRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataActivitiesListRepository extends ActivitiesListRepository, JpaRepository<ActivitiesList, Long> {
}
