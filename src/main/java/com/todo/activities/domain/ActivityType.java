package com.todo.activities.domain;

import com.ddd.common.domain.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Entity;
import java.time.Duration;
import java.util.Objects;

@Entity
@AllArgsConstructor
@Getter
public class ActivityType extends AbstractEntity {
    private String description;
    private Duration expectedDuration;
    private long multiplier;

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
