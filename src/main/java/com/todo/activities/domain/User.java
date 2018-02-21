package com.todo.activities.domain;

import com.ddd.common.domain.AbstractEntity;
import com.ddd.common.validation.Contract;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.*;

@Entity
@Table( name = "Account" )
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class User extends AbstractEntity {

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
    private List<ActivitiesList> activities = new ArrayList<>();

    public void addActivities(ActivitiesList activitiesList){
        Contract.notNull(activitiesList);
        this.activities.add(activitiesList);
    }

    public List<ActivitiesList> getActivitiesList(){
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
