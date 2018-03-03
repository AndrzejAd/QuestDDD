package com.quest.registering.saving.domain;

import com.ddd.common.validation.Contract;
import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@EqualsAndHashCode(of = "address")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class Email  {
    @Getter
    private String address;

    public Email(String address) {
        Contract.matches(address, ".*@.*");
        this.address = address;
    }
}
