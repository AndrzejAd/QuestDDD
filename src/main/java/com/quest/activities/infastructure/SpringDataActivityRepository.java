package com.quest.activities.infastructure;

import com.quest.activities.domain.activity.Activity;
import com.quest.activities.domain.activity.ActivityRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface SpringDataActivityRepository extends ActivityRepository, JpaRepository<Activity, Long> {
    Collection<Activity> findByLatitudeGreaterThanAndLatitudeLessThanAndLongitudeGreaterThanAndLongitudeLessThan(
            double minLatitude, double maxLatitude, double minLongitude, double maxLongitude
    );
}
