package com.todo.registering.saving.domain;

import com.todo.common.annotations.DomainFactory;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@DomainFactory
public class UserFactory {

    public User createUser(
            final String emailAddress,
            final String country,
            final String city,
            final String username,
            final String password,
            final long birthDate) {
        Address address = new Address(country, city);

        Email email = new Email(emailAddress);

        LocalDate jDate = LocalDate.now();
        LocalDate bDate = Instant.ofEpochMilli(birthDate).atZone(ZoneId.systemDefault()).toLocalDate();

        User user = new User(email, address, username, password, jDate, bDate);

        return user;
    }


}
