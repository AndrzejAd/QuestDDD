package com.ddd.common.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@MappedSuperclass
@ToString
@Getter
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Version
    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int version;

    protected final LocalDate creationDate;

    public AbstractEntity() {
        creationDate = LocalDate.now();
    }

    @Override
    abstract public int hashCode();

    @Override
    abstract public boolean equals(Object obj);

}
