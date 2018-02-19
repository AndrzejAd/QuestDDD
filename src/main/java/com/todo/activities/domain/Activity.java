package com.todo.activities.domain;

import com.ddd.common.domain.AbstractEntity;
import com.ddd.common.validation.Contract;
import com.ddd.common.validation.ContractBroken;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Activity extends AbstractEntity{
    private long baseAward;
    private long totalAward;
    private Progress progress;
    private LocalDateTime startDate;
    private boolean isDone;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn
    private ActivityType activityType;

    @ManyToOne
    @JoinColumn(name="activities_id", nullable = false, referencedColumnName = "id")
    private Activities activities;

    public Activity(ActivityType activityType, Activities activities) {
        this.activityType = activityType;
        this.activities = activities;
        this.baseAward = 1000;
        this.totalAward = baseAward;
        this.progress = Progress.NOTDONE;
        this.startDate = LocalDateTime.MIN;
        this.isDone = false;
    }

    public void startActivity(){
        Contract.isTrue( progress != Progress.DONE && progress != Progress.BEINGDONE, ProgressAlreadyStarted::new );
        progress = Progress.BEINGDONE;
    }

    public void finishActivity(){
        Contract.isTrue( progress != Progress.BEINGDONE, ProgressNotStarted::new );
        progress = Progress.DONE;
        isDone = true;
    }

    public void changeTotalAward(long award){
        totalAward = award;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }

    private class ProgressAlreadyStarted extends ContractBroken{}

    private class ProgressNotStarted extends ContractBroken{}
}
