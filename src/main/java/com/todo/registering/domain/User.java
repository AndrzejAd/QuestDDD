package com.todo.registering.domain;

import com.todo.common.domain.AbstractEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@EqualsAndHashCode(callSuper = false, of = "email")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@ToString
@Getter
public class User extends AbstractEntity{

    @Embedded
    @Column(unique = true)
    private Email email;

    @Embedded
    private Address address;

    private String username;
    private String password;
    private LocalDate joiningDate;
    private LocalDate birthDate;


}
