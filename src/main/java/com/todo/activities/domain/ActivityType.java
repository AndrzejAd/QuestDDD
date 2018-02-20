package com.todo.activities.domain;

import com.ddd.common.domain.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.time.Duration;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
public class ActivityType extends AbstractEntity {
    private String description;
    private Duration expectedDuration;
    private long baseAward;

    @OneToMany( mappedBy = "activityType", fetch = FetchType.LAZY)
    private Set<Activity> activity = new HashSet<>();

    public ActivityType(String description, Duration expectedDuration, long baseAward) {
        this.description = description;
        this.expectedDuration = expectedDuration;
        this.baseAward = baseAward;
    }

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
