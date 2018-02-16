package com.todo.activities.domain;

import com.ddd.common.domain.AbstractEntity;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class Activity extends AbstractEntity{
    private long userId;
    private ActivityType activityType;
    private long award;
    private Progress progress;
    private LocalDateTime statDate;
    private boolean isDone;

    public Activity(long userId, ActivityType activityType, long award, Progress progress,
                    LocalDateTime statDate, boolean isDone) {
        this.userId = userId;
        this.activityType = activityType;
        this.award = award;
        this.progress = progress;
        this.statDate = statDate;
        this.isDone = isDone;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }
}
