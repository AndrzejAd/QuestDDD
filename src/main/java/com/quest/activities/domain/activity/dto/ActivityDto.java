package com.quest.activities.domain.activity.dto;

import com.quest.activities.domain.activity.ActivitiesList;
import com.quest.activities.domain.activity.ActivityType;
import lombok.Builder;
import lombok.Value;
import java.time.LocalDateTime;

@Value @Builder
public class ActivityDto {
    private long award;
    private LocalDateTime startDate;
    private LocalDateTime finishDateTime;
    private boolean isDone;
    private double longitude;
    private double latitude;
    private Progress progress;
    private ActivityType activityType;
    private ActivitiesList activitiesList;
}
