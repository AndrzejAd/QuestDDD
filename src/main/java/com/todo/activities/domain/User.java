package com.todo.activities.domain;

import com.ddd.common.domain.AbstractEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table( name = "Account" )
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class User extends AbstractEntity {

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
    private Set<Activities> activities = new HashSet<>();

    public void addActivities(Activities activities){

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
