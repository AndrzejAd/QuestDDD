package com.todo.activities.domain;

import com.ddd.common.annotations.AggregateRoot;
import com.ddd.common.domain.AbstractEntity;
import com.ddd.common.validation.Contract;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


@AggregateRoot
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Activities extends AbstractEntity {

    @Getter
    @Formula(value = "SELECT SUM( A.TOTAL_AWARD) \n" +
            "FROM ACTIVITY A \n" +
            "WHERE A.ACTIVITIES_ID = id")
    private double totalExperience;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "activities")
    private Set<Activity> activities = new HashSet<>();

    public Activities(User user) {
        this.totalExperience = 0;
        this.user = user;
    }

    public void addActivity(Activity activity){
        Contract.notNull(activity);
        activities.add(activity);

    }

    public void updateTotalExperience(){
        /*totalExperience = activities
                .stream()
                .filter( (activity) -> activity.getProgress() == Progress.DONE)
                .mapToDouble(Activity::getTotalAward)
                .sum();*/
    }

    public Set<Activity> getActivities() {
        return Collections.unmodifiableSet(activities);
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
