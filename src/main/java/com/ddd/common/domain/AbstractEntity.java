package com.ddd.common.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@MappedSuperclass
@ToString
@Getter
public abstract class AbstractEntity  extends PersistentClass{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public AbstractEntity() {
        super();
    }

    @Override
    abstract public int hashCode();

    @Override
    abstract public boolean equals(Object obj);

}
