package com.todo.activities.domain;

import com.ddd.common.domain.AbstractEntity;
import com.ddd.common.validation.Contract;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Formula;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.*;

@Entity
@Table( name = "Account" )
public class User extends AbstractEntity {
    @Getter
    private int level;

    @Getter
    private double totalUserExperience;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
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
