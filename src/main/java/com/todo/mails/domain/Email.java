package com.todo.mails.domain;

import com.todo.common.annotations.ValueObject;
import com.todo.common.validation.Contract;
import lombok.Getter;

import java.time.LocalDate;

@ValueObject
@Getter
public class Email {
    private final String address;
    private final String subject;
    private final String text;
    private final LocalDate sendDate;

    public Email(String address, String subject, String text, LocalDate sendDate) {
        Contract.notNull(address, subject, text, sendDate);
        Contract.matches(address, ".*@.*");
        this.address = address;
        this.subject = subject;
        this.text = text;
        this.sendDate = sendDate;
    }


}
