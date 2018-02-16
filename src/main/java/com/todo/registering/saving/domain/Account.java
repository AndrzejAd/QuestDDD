package com.todo.registering.saving.domain;

import com.ddd.common.annotations.AggregateRoot;
import com.ddd.common.domain.AbstractEntity;
import com.ddd.common.validation.Contract;
import com.ddd.common.validation.InvalidDate;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@AggregateRoot
@ToString
@Getter
@EqualsAndHashCode(callSuper = false, of = "email")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Account extends AbstractEntity{

    @Embedded
    @Column(unique = true)
    private Email email;

    @Embedded
    private Address address;

    private String username;
    private String password;
    private LocalDate joiningDate;
    private LocalDate birthDate;


    public Account(Email email, Address address, String username, String password, LocalDate joiningDate, LocalDate birthDate) {
        Contract.notNull(email, address, username, password, joiningDate, birthDate);
        Contract.matchesSize(username, 3, 100);
        Contract.matchesSize(password, 3, 30);
        Contract.isTrue(birthDate.isBefore(LocalDate.now()), InvalidDate::new);
        Contract.isTrue(LocalDate.now().minusYears(birthDate.getYear()).getYear() < 120, InvalidDate::new);
        this.email = email;
        this.address = address;
        this.username = username;
        this.password = password;
        this.joiningDate = joiningDate;
        this.birthDate = birthDate;
    }
}
