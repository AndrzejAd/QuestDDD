package com.quest.accounts.saving.domain;

import com.ddd.common.annotations.DomainFactory;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@DomainFactory
public class AccountFactory {

    public Account createUser(
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

        return new Account(email, address, username, password, jDate, bDate);
    }


}
