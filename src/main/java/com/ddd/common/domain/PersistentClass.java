package com.ddd.common.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.time.LocalDate;

@MappedSuperclass
@ToString
@Getter
public abstract class PersistentClass {

    @Version
    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int version;

    protected final LocalDate creationDate;

    public PersistentClass() {
        creationDate = LocalDate.now();
    }

    @Override
    abstract public int hashCode();

    @Override
    abstract public boolean equals(Object obj);

}
