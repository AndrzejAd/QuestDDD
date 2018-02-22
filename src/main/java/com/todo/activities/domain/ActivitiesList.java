package com.todo.activities.domain;

import com.ddd.common.annotations.AggregateRoot;
import com.ddd.common.domain.AbstractEntity;
import com.ddd.common.validation.Contract;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.*;


@AggregateRoot
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ActivitiesList extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
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

    public List<Activity> getActivities() {
        return Collections.unmodifiableList(activities);
    }

    public double getTotalExperienceForThisList(){
        return activities
                .stream()
                .mapToDouble(Activity::getTotalAward)
                .sum();
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }

    @Override
    public String toString() {
        return "ActivitiesList{" +
                ", userID=" + user.getId() +
                ", activities=" + activities +
                '}';
    }
}
