package com.todo.activities.domain;

import com.ddd.common.annotations.AggregateRoot;
import com.ddd.common.domain.AbstractEntity;
import com.ddd.common.validation.Contract;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.*;


@AggregateRoot
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ActivitiesList extends AbstractEntity {

    @Getter
    @Formula(value = "SELECT SUM( A.TOTAL_AWARD) \n" +
            "FROM ACTIVITY A \n" +
            "WHERE A.ACTIVITIES_LIST_ID = id")
    private double totalExperience;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "activitiesList")
    private List<Activity> activities = new ArrayList<>();

    public ActivitiesList(User user) {
        super();
        this.totalExperience = 0;
        this.user = user;
    }

    public void addActivity(Activity activity){
        Contract.notNull(activity);
        activities.add(activity);
    }

    public List<Activity> getActivities() {
        return Collections.unmodifiableList(activities);
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
