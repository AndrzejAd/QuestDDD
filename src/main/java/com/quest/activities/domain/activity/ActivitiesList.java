package com.quest.activities.domain.activity;

import com.ddd.common.annotations.AggregateRoot;
import com.ddd.common.domain.AbstractEntity;
import com.ddd.common.validation.Contract;
import com.quest.activities.domain.activity.dto.ActivitiesListDto;
import com.quest.activities.domain.activity.dto.Progress;
import com.quest.activities.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.*;


@AggregateRoot
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ActivitiesList extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    @Getter
    private User user;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "activitiesList")
    private List<Activity> activities = new ArrayList<>();

    public ActivitiesList(User user) {
        super();
        this.user = user;
    }

    public void addActivity(Activity activity){
        Contract.notNull(activity);
        activities.add(activity);
    }

    public double getTotalExperienceForThisList(){
        return activities
                .stream()
                .mapToDouble(Activity::getAward)
                .sum();
    }

    public double getPossibleExperienceForUnfinishedActivities(){
        return activities.stream()
                .filter( activity -> activity.getProgress() == Progress.NOTDONE)
                .mapToDouble( x -> x.getActivityType().getBaseAward())
                .sum();
    }

    public Optional<Activity> getOldestStartedButNotFinishedActivity(){
        return activities.stream()
                .filter( activity -> activity.getProgress() == Progress.BEINGDONE) // ensures Optional wont get me null
                .sorted( (ac1, ac2) ->
                        (int) (ac1.getStartDate().get().toEpochSecond(ZoneOffset.UTC) - ac1.getStartDate().get().toEpochSecond(ZoneOffset.UTC) )
                 )
                .findFirst();
    }

    public List<Activity> getActivities() {
        return Collections.unmodifiableList(activities);
    }

    public ActivitiesListDto dto(){
        return ActivitiesListDto.builder()
                .user(user)
                .activities(activities)
                .build();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ActivitiesList)) return false;
        ActivitiesList that = (ActivitiesList) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public String toString() {
        return "ActivitiesList{" +
                ", userID=" + user.getId() +
                ", activities=" + activities +
                '}';
    }


}
