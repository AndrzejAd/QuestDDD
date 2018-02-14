package com.todo.common.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@MappedSuperclass
@ToString
@Getter
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Version
    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int version;

    private LocalDate creationDate;

    @Override
    abstract public int hashCode();

    @Override
    abstract public boolean equals(Object obj);

}
