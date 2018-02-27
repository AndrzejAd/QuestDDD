package com.todo.location.domain;

import com.ddd.common.domain.AbstractEntity;
import lombok.Getter;

import javax.persistence.Entity;

@Entity
@Getter
public class ActivitiesLocation extends AbstractEntity{
    private double longitude;
    private double latitude;

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }
}
