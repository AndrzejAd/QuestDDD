package com.todo.activities.domain.location.domain;

import com.ddd.common.domain.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Account")
public class User extends AbstractEntity {
    private String username;
    private int level;

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }
}
