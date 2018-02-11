package com.todo.registering.domain;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@EqualsAndHashCode(of = {"country", "city"})
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@ToString
public class Address {
    private String country;
    private String city;
}
