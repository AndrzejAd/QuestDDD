package com.quest.accounts.saving.domain;

import com.ddd.common.validation.Contract;
import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@EqualsAndHashCode(of = {"country", "city"})
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
public class Address {
    private String country;
    private String city;

    public Address(String country, String city) {
        Contract.notNull(country, city);
        Contract.matchesSize(country, 3, 100);
        Contract.matchesSize(city, 3, 50);
        Contract.matches(country, "[a-zA-Z\\s]+");
        Contract.matches(city, "[a-zA-Z\\s]+");
        this.country = country;
        this.city = city;
    }
}
