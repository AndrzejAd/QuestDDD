package com.quest.activities.domain.activity;

import com.ddd.common.domain.AbstractEntity;
import com.ddd.common.validation.Contract;
import com.ddd.common.validation.ContractBroken;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Entity
@Getter @NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Activity extends AbstractEntity{
    private long award;
    private LocalDateTime startDate;
    private LocalDateTime finishDateTime;
    private boolean isDone;
    private long longitude;
    private long latitude;

    @Enumerated(value = EnumType.STRING)
    private Progress progress;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "activity_type_id", nullable = false, referencedColumnName = "id")
    private ActivityType activityType;

    @ManyToOne
    @JoinColumn(name="activities_list_id", nullable = false, referencedColumnName = "id")
    private ActivitiesList activitiesList;

    public Activity(ActivityType activityType, ActivitiesList activitiesList, long longitude, long latitude) {
        super();
        Contract.isBetween(longitude, -180,180,   WrongLatitudeOrLongitude::new);
        Contract.isBetween(latitude, -85.05,85.05,  WrongLatitudeOrLongitude::new);
        this.activityType = activityType;
        this.activitiesList = activitiesList;
        this.award = 0;
        this.progress = Progress.NOTDONE;
        this.isDone = false;
        this.longitude = longitude;
        this.latitude = latitude;
    }


    public void startActivity(){
        Contract.isTrue( progress == Progress.NOTDONE, ActivityAlreadyStarted::new );
        startDate = LocalDateTime.now();
        progress = Progress.BEINGDONE;
    }

    /*
        Award is only counted after finishing activity.
     */
    public void finishActivity(){
        Contract.isTrue( progress == Progress.BEINGDONE, ActivityNotStarted::new );
        progress = Progress.DONE;
        isDone = true;
        finishDateTime = LocalDateTime.now();
    }

    public void setAward(long award){
        this.award = award;
    }


    public Optional<LocalDateTime> getStartDate() {
        return Optional.ofNullable(startDate);
    }

    public Optional<LocalDateTime> getFinishDateTime() {
        return Optional.of(finishDateTime);
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

    @Override
    public String toString() {
        return "Activity{" +
                "award=" + award +
                ", startDate=" + startDate +
                ", isDone=" + isDone +
                ", progress=" + progress +
                ", activityType=" + activityType +
                ", activitiesListId=" + activitiesList.getId() +
                '}';
    }

    public class ActivityAlreadyStarted extends ContractBroken{}

    public class ActivityNotStarted extends ContractBroken{}

    public class WrongLatitudeOrLongitude extends ContractBroken{}

}
