package com.todo.activities.managment.domain;

import com.ddd.common.domain.AbstractEntity;
import com.ddd.common.validation.Contract;
import lombok.AccessLevel;
import lombok.Getter;

import javax.persistence.*;
import java.util.*;

@Entity
@Table( name = "Account" )
@Getter
public class User extends AbstractEntity {
    private String username;
    private int level;
    private double totalUserExperience;

    @Column(name = "address")
    private String emailAddress;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
    @Getter(value = AccessLevel.NONE)
    private List<ActivitiesList> activities = new ArrayList<>();

    public User() {
        super();
        this.level = 0;
        this.totalUserExperience = 0;
    }

    public void addActivities(ActivitiesList activitiesList){
        Contract.notNull(activitiesList);
        this.activities.add(activitiesList);
    }

    public List<ActivitiesList> getActivitiesList(){
        return Collections.unmodifiableList(activities);
    }

    public double getUserExperience(){
        return activities
                .stream()
                .mapToDouble(ActivitiesList::getTotalExperienceForThisList)
                .sum();
    }

    public void addExperience(double experience){
        totalUserExperience += experience;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User that = (User) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public String toString() {
        return "User{" +
                "activities=" + activities +
                '}';
    }
}
