package com.todo.activities.domain;

import com.ddd.common.domain.AbstractEntity;

import javax.persistence.Entity;
import java.time.Duration;
import java.util.Objects;

@Entity
public class ActivityType extends AbstractEntity {
    private String description;
    private Duration expectedDuration;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ActivityType)) return false;
        ActivityType that = (ActivityType) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
