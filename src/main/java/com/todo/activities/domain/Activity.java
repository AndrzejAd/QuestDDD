package com.todo.activities.domain;

import com.ddd.common.domain.AbstractEntity;
import com.ddd.common.validation.Contract;
import com.ddd.common.validation.ContractBroken;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Activity extends AbstractEntity{
    private long totalAward;
    private LocalDateTime startDate;
    private boolean isDone;
    private double multiplier;

    @Enumerated(value = EnumType.STRING)
    private Progress progress;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "activity_type_id", nullable = false, referencedColumnName = "id")
    private ActivityType activityType;

    @ManyToOne
    @JoinColumn(name="activities_list_id", nullable = false, referencedColumnName = "id")
    private ActivitiesList activitiesList;

    public Activity(ActivityType activityType, ActivitiesList activitiesList) {
        this.activityType = activityType;
        this.activitiesList = activitiesList;
        this.totalAward = 0;
        this.progress = Progress.NOTDONE;
        this.startDate = LocalDateTime.MIN;
        this.isDone = false;
        this.multiplier = 1;
    }

    public void startActivity(){
        Contract.isTrue( progress == Progress.NOTDONE, ActivityAlreadyStarted::new );
        progress = Progress.BEINGDONE;
    }

    /*
        Award is only counted after finishing activity.
     */
    public void finishActivity(){
        Contract.isTrue( progress == Progress.BEINGDONE, ActivityNotStarted::new );
        progress = Progress.DONE;
        isDone = true;
        totalAward += activityType.getBaseAward() * multiplier;
    }


    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Activity)) return false;
        Activity that = (Activity) o;
        return Objects.equals(getId(), that.getId());
    }

    protected class ActivityAlreadyStarted extends ContractBroken{}

    protected class ActivityNotStarted extends ContractBroken{}
}
